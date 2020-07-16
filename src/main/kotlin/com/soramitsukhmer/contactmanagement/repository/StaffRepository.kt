package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Staff
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface StaffRepository : PagingAndSortingRepository<Staff, Long>, JpaSpecificationExecutor<Staff> {
}