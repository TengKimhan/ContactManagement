package com.soramitsukhmer.contactmanagement.api.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.soramitsukhmer.contactmanagement.common.Constants
import com.soramitsukhmer.contactmanagement.domain.model.Status
import jdk.jshell.Snippet
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CompanyDTO(
        val id: Long,
        val name: String,
        val phone: String,
        val webUrl: String?,
        val status:StatusDTO,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val createdAt: LocalDateTime,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val updatedAt: LocalDateTime
)

data class FilterParamCompanyDTO(
        val q: String?,
        val statusId:Long?
)

data class RequestCompanyDTO(
        @field:NotEmpty val name: String,
        @field:NotEmpty val phone: String,
        val webUrl: String?,
        @field:NotNull val status: Long = 1
)