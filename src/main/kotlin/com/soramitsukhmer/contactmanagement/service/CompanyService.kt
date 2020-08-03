package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.exception.ChildFoundException
import com.soramitsukhmer.contactmanagement.api.exception.FieldNotFoundException
import com.soramitsukhmer.contactmanagement.api.request.*
import com.soramitsukhmer.contactmanagement.api.response.PageResponse
import com.soramitsukhmer.contactmanagement.domain.model.Company
import com.soramitsukhmer.contactmanagement.domain.model.CompanyLocation
import com.soramitsukhmer.contactmanagement.domain.model.Staff
import com.soramitsukhmer.contactmanagement.domain.model.Status
import com.soramitsukhmer.contactmanagement.domain.spec.CompanySpec
import com.soramitsukhmer.contactmanagement.domain.spec.StaffSpec
import com.soramitsukhmer.contactmanagement.service.validation.CompanyValidationService
import com.soramitsukhmer.contactmanagement.helper.toPageResponse
import com.soramitsukhmer.contactmanagement.repository.*
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class CompanyService(
        val companyRepository: CompanyRepository,
        val statusRepository: StatusRepository,
        val locationRepository: LocationRepository,
        val companyLocationRepository: CompanyLocationRepository,
        val companyValidationService: CompanyValidationService,
        val staffRepository: StaffRepository
){
//    fun listAllCompanies() : List<CompanyDTO>{
//
//        return companyRepository.findAll().map { it.toDTO() }
//    }
//
//    fun listAllCompanies(firstPageWithTwoElements:Pageable): List<CompanyDTO>{
//        val page: Page<Company> = companyRepository.findAll(firstPageWithTwoElements)
//
//        val allCompanies : List<Company> = page.content
//
//        return allCompanies.map { it.toDTO() }
//    }

//    fun listCompanies(q : String?, pageable: Pageable): Pair<List<CompanyDTO>, Pagination>
//    {
//        val querySpec = q?.let { CompanySpec.genSearchSpec(it.toLowerCase()) }
//        val page : Page<CompanyDTO> = companyRepository.findAll( Specification.where(querySpec), pageable).map { it.toDTO() }
//        return Pair(page.content, Pagination(page.number ,page.size, page.totalElements, page.totalPages))
//    }

    fun listCompanies(filterParamCompanyDTO: FilterParamCompanyDTO?, pageable: Pageable): PageResponse<CompanyDTO>
    {
        val querySpec = filterParamCompanyDTO?.q?.let { CompanySpec.genSearchSpec(it.toLowerCase()) }
        val statusSpec = filterParamCompanyDTO?.statusId?.let { CompanySpec.genFilterStatus(it) }
        return companyRepository.findAll( Specification.where(querySpec)?.and(statusSpec), pageable).map { it.toDTO() }.toPageResponse()
    }

    fun getCompany(id: Long) : CompanyDTO{
        return companyRepository.findById(id).orElseThrow{
            throw FieldNotFoundException(Company::class.simpleName.toString(), "$id")
        }.toDTO()
    }

    fun createCompany(reqCompanyDTO: RequestCompanyDTO) : CompanyDTO{

        val status = statusRepository.findById(reqCompanyDTO.status).orElseThrow{
            throw FieldNotFoundException(Status::class.simpleName.toString(), "$reqCompanyDTO.status")
        }
        // Create Company

        // Result Company ID

        val companyLocationList = mutableListOf<CompanyLocation>()
        val company = Company.fromReqDTO(reqCompanyDTO, status)

        locationRepository.findAllById(reqCompanyDTO.locations).map {
            val companyLocation = CompanyLocation().apply {
                this.company = company
                this.location = it
            }
            companyLocationRepository.save(companyLocation)
            companyLocationList.add(companyLocation)
        }
        company.companyLocations = companyLocationList

        val newCompany = Company.fromReqDTO(reqCompanyDTO, status)

        companyValidationService.validateUniquePhone(null, newCompany.phone, newCompany.name)

        return companyRepository.save(newCompany).toDTO()
    }

    fun createCompanyWithStaffs(req: RequestCompanyWithStaffsDTO) : String{
        val companyDTO = RequestCompanyDTO(
                name = req.name,
                phone = req.phone,
                webUrl = req.webUrl,
                status = req.status,
                locations = req.location
        )
        val createdCompany = createCompany(companyDTO)
        req.staffs.map {staffDTO ->
            val company = companyRepository.findById(createdCompany.id).orElseThrow {
                throw RuntimeException("Created Company ID: [${createdCompany.id}] is not found.")
            }
            val status = statusRepository.findById(staffDTO.status).orElseThrow {
                throw RuntimeException("Staff status ID: [${staffDTO.status}] is not found.")
            }
            val staff = Staff.fromReqDTO(staffDTO, company, status)
            staffRepository.save(staff)
        }
        return "done"
    }

    fun updateCompany(id: Long, reqCompanyDTO: RequestCompanyDTO) : CompanyDTO{
        val status = statusRepository.findById(reqCompanyDTO.status).orElseThrow{
            throw FieldNotFoundException(Status::class.simpleName.toString(), "$reqCompanyDTO.status")
        }

        val company = companyRepository.findById(id).orElseThrow{
            throw FieldNotFoundException(Company::class.simpleName.toString(), "$id")
        }.updateCompany(reqCompanyDTO, status)

        companyValidationService.validateUniquePhone(company.id, company.phone, company.name)

        return companyRepository.save(company).toDTO()
    }

    fun deleteCompany(id: Long) : String{
        val company = companyRepository.findById(id).orElseThrow{
            throw FieldNotFoundException(Company::class.simpleName.toString(), "$id")
        }

        val companySpec = company.id?.let { StaffSpec.genFilterCompany(it) }
        val staff = staffRepository.findAll(Specification.where(companySpec))

        if(staff.isEmpty()) companyRepository.delete(company)
        else throw ChildFoundException("Delete can't be perform because company has staffs.")

        return "DELETED"
    }
}

