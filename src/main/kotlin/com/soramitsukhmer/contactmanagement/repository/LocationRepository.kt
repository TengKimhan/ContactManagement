package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Location
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface LocationRepository : CrudRepository<Location, Long>, PagingAndSortingRepository<Location, Long>{
}