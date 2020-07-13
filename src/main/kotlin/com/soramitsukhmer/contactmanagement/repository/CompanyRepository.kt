package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Company
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CompanyRepository : CrudRepository<Company, Long>{
}