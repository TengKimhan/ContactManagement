package com.soramitsukhmer.contactmanagement.helper

import com.soramitsukhmer.contactmanagement.api.response.PageResponse
import com.soramitsukhmer.contactmanagement.domain.model.Pagination
import org.springframework.data.domain.Page

fun <T> Page<T>.toPageResponse(): PageResponse<T> {
    return PageResponse(
            content = this.content,
            pagination = Pagination(
                    currentPage = this.pageable.pageNumber ,
                    pageSize    = this.pageable.pageSize,
                    totalElements = this.totalElements,
                    totalPages = this.totalPages
            )
    )
}