package com.soramitsukhmer.contactmanagement.api.response

import com.soramitsukhmer.contactmanagement.domain.model.Pagination

data class PageResponse<T>(val content: List<T>, val pagination: Pagination) {}