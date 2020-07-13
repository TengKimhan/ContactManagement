package com.soramitsukhmer.contactmanagement.api.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.soramitsukhmer.contactmanagement.common.Constants
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty

data class CompanyDTO(
        val id: Long,
        val name: String,
        val phone: String,
        val webUrl: String?,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val createdAt: LocalDateTime,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val updatedAt: LocalDateTime
)

data class RequestCompanyDTO(
        @field:NotEmpty val name: String,
        @field:NotEmpty val phone: String,
        val webUrl: String?
)