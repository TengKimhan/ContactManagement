package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Company
import com.soramitsukhmer.contactmanagement.domain.model.Staff
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface CompanyRepository : CrudRepository<Company, Long>, PagingAndSortingRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    fun existsCompanyByPhone(phone: String): Boolean
    fun existsCompanyByPhoneAndIdIsNot(phone: String, id: Long): Boolean
    fun existsCompanyByPhoneOrName(phone: String, name: String): Boolean
    fun existsCompanyByPhoneOrNameAndIdIsNot(phone: String, name: String, id: Long): Boolean
    fun existsCompanyByNameAndIdIsNot(name: String, id: Long) : Boolean
    fun existsCompanyByPhoneAndNameAndIdIsNot(phone: String, name: String, id: Long): Boolean

}