package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.exception.FieldNotFoundException
import com.soramitsukhmer.contactmanagement.api.request.RequestStatusDTO
import com.soramitsukhmer.contactmanagement.api.request.StatusDTO
import com.soramitsukhmer.contactmanagement.domain.model.Company
import com.soramitsukhmer.contactmanagement.domain.model.Status
import com.soramitsukhmer.contactmanagement.repository.StatusRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class StatusService(val statusRepository: StatusRepository) {

    fun getAllStatus(pageable: Pageable):Page<StatusDTO>{
        return statusRepository.findAll(pageable).map { it.toDTO() }
    }

    fun getStatus(id:Long):StatusDTO{
        return statusRepository.findById(id).orElseThrow(){
            throw FieldNotFoundException(Status::class.simpleName.toString(), "$id")
        }.toDTO()
    }

    fun createStatus(requestStatusDTO: RequestStatusDTO):StatusDTO{
        val newStatus = Status.fromReqDTO(requestStatusDTO)
        return statusRepository.save(newStatus).toDTO()
    }

    fun updateStatus(id:Long, requestStatusDTO: RequestStatusDTO):StatusDTO{
        val updateStatus = statusRepository.findById(id).orElseThrow()
        {
            throw FieldNotFoundException(Status::class.simpleName.toString(), "$id")
        }.updateStatus(requestStatusDTO)

        return statusRepository.save(updateStatus).toDTO()
    }
}