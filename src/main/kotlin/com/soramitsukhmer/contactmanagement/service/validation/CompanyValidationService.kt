package com.soramitsukhmer.contactmanagement.service.validation

import com.soramitsukhmer.contactmanagement.api.exception.RecordIsAlreadyExistException
import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyValidationService(val companyRepository: CompanyRepository){

    fun validateUniquePhone(companyId: Long?, phone: String, name:String){
//        when(companyId)
//        {
//            null -> if(companyRepository.existsCompanyByPhoneOrName(phone, name))
//                throw RecordIsAlreadyExistException("Phone or Name must be unique")
//            else -> if(companyRepository.existsCompanyByPhoneAndNameAndIdIsNot(phone, name, companyId))
//                throw RecordIsAlreadyExistException("[Phone:$phone] or [Name:$name] is already existed.")
//        }

        companyId?.let {
//            if(companyRepository.existsCompanyByPhoneOrNameAndIdIsNot(phone, name, it)) throw RecordIsAlreadyExistException("[Phone:$phone] or [Name:$name] is already exist.")
            if(companyRepository.existsCompanyByPhoneAndIdIsNot(phone, it) || companyRepository.existsCompanyByNameAndIdIsNot(name, it))
                throw RecordIsAlreadyExistException("[Phone:$phone] or [Name:$name] is already exist.")
        } ?: run{
            if(companyRepository.existsCompanyByPhoneOrName(phone, name))
                throw RecordIsAlreadyExistException("Phone or Name must be unique")
        }
    }
}