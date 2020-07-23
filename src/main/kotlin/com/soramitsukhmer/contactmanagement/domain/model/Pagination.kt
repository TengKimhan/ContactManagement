package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.CompanyDTO
import org.springframework.data.domain.Page

data class Pagination(val currentPage:Int, val pageSize:Int, val totalElements:Long, val totalPages:Int) {}