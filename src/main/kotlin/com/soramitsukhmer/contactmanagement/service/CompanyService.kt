package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.exception.ChildFoundException
import com.soramitsukhmer.contactmanagement.api.exception.FieldNotFoundException
import com.soramitsukhmer.contactmanagement.api.request.*
import com.soramitsukhmer.contactmanagement.api.response.PageResponse
import com.soramitsukhmer.contactmanagement.domain.model.Company
import com.soramitsukhmer.contactmanagement.domain.model.Status
import com.soramitsukhmer.contactmanagement.domain.spec.CompanySpec
import com.soramitsukhmer.contactmanagement.domain.spec.StaffSpec
import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import com.soramitsukhmer.contactmanagement.repository.StatusRepository
import com.soramitsukhmer.contactmanagement.service.validation.CompanyValidationService
import com.soramitsukhmer.contactmanagement.helper.toPageResponse
import com.soramitsukhmer.contactmanagement.repository.StaffRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class CompanyService(
        val companyRepository: CompanyRepository,
        val statusRepository: StatusRepository,
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

        val newCompany = Company.fromReqDTO(reqCompanyDTO, status)

        companyValidationService.validateUniquePhone(null, newCompany.phone, newCompany.name)

        return companyRepository.save(newCompany).toDTO()
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
        val staff = staffRepository.findAll(Specification.where(companySpec)).toList()

        if(staff.isEmpty()) companyRepository.delete(company)
        else throw ChildFoundException("Delete can't be perform because company has staffs.")

        return "DELETED"
    }
}

