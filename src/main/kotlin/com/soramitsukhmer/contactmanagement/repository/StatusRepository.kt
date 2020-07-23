package com.soramitsukhmer.contactmanagement.repository

import com.soramitsukhmer.contactmanagement.domain.model.Status
import jdk.jshell.Snippet
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface StatusRepository : CrudRepository<Status, Long>, PagingAndSortingRepository<Status, Long>{
}