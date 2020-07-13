package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Staff
import org.springframework.data.repository.CrudRepository

interface StaffRepository : CrudRepository<Staff, Long>{
}