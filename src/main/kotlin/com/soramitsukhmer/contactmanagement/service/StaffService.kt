package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.exception.FieldNotFoundException
import com.soramitsukhmer.contactmanagement.api.request.FilterParamsStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.StaffDTO
import com.soramitsukhmer.contactmanagement.api.response.PageResponse
import com.soramitsukhmer.contactmanagement.domain.model.Company
import com.soramitsukhmer.contactmanagement.domain.model.Staff
import com.soramitsukhmer.contactmanagement.domain.model.Status
import com.soramitsukhmer.contactmanagement.domain.spec.StaffSpec
import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import com.soramitsukhmer.contactmanagement.repository.StaffRepository
import com.soramitsukhmer.contactmanagement.repository.StatusRepository
import com.soramitsukhmer.contactmanagement.helper.toPageResponse
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class StaffService(
        val staffRepository: StaffRepository,
        val companyRepository: CompanyRepository,
        val statusRepository: StatusRepository
){

    fun listAllStaffs(filterParamsStaffDTO: FilterParamsStaffDTO?, pageable: Pageable) : PageResponse<StaffDTO> {
        val querySpec = filterParamsStaffDTO?.q?.let { StaffSpec.genSearchSpec(it.toLowerCase()) }
        val companySpec = filterParamsStaffDTO?.companyId?.let { StaffSpec.genFilterCompany(it) }
        val statusSpec = filterParamsStaffDTO?.statusId?.let { StaffSpec.genFilterStatus(it) }
        return staffRepository.findAll(
                Specification.where(companySpec)?.and(querySpec)?.and(statusSpec),
                pageable
        ).map{ it.toDTO() }.toPageResponse()
    }

    fun getStaff(id : Long) : StaffDTO{
        return staffRepository.findById(id).orElseThrow {
            throw FieldNotFoundException(Staff::class.simpleName.toString(), "$id")
        }.toDTO()
    }

    fun createStaff(reqStaffDTO: RequestStaffDTO) : StaffDTO {
        val company = companyRepository.findById(reqStaffDTO.company).orElseThrow {
            throw FieldNotFoundException(Company::class.simpleName.toString(), "${reqStaffDTO.company}")
        }

        val status = statusRepository.findById(reqStaffDTO.status).orElseThrow{
            throw FieldNotFoundException(Status::class.simpleName.toString(), "${reqStaffDTO.status}")
        }

        val newStaff = Staff.fromReqDTO(reqStaffDTO, company, status)
        return staffRepository.save(newStaff).toDTO()
    }

    fun updateStaff(id: Long, reqStaffDTO: RequestStaffDTO) : StaffDTO{
        val company = companyRepository.findById(reqStaffDTO.company).orElseThrow {
            throw FieldNotFoundException(Company::class.simpleName.toString(), "${reqStaffDTO.company}")
        }

        val status = statusRepository.findById(reqStaffDTO.status).orElseThrow{
            throw FieldNotFoundException(Status::class.simpleName.toString(), "${reqStaffDTO.status}")
        }

        val staff = staffRepository.findById(id).orElseThrow {
            throw FieldNotFoundException(Staff::class.simpleName.toString(), "$id")
        }
        return staffRepository.save(staff.updateStaff(reqStaffDTO, company,status)).toDTO()
    }

    fun deleteStaff(id: Long) : String {
        val staff = staffRepository.findById(id).orElseThrow {
            throw FieldNotFoundException(Staff::class.simpleName.toString(), "$id")
        }
        staffRepository.delete(staff)
        return "DELETED"
    }

//    fun updateStaff(id: Long, reqStaffDTO: RequestStaffDTO) : StaffDTO {
//        val company = companyRepository.findById(reqStaffDTO.company).orElseThrow {
//            throw RuntimeException("CompanyId[${reqStaffDTO.company}] is not found.")
//        }
//        val staff = staffRepository.findById(id).orElseThrow{
//            throw RuntimeException("Staff[$id] is not found.")
//        }.updateStaff(reqStaffDTO, company)
//
//        return staffRepository.save(staff).toDTO()
//    }

//    fun deleteStaff(id: Long){
//        val staff = staffRepository.findById(id).orElseThrow{
//            throw RuntimeException("Staff [$id] is not found")
//        }
//        staffRepository.delete(staff)
//    }

    fun getStaffByName(name:String): StaffDTO{
        return staffRepository.findByName(name).toDTO()
    }
}