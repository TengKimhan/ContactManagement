package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.CompanyLocation
import org.springframework.data.repository.CrudRepository

interface CompanyLocationRepository : CrudRepository<CompanyLocation, Long> {
}