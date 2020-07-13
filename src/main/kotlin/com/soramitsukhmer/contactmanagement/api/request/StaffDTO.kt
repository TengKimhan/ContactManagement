package com.soramitsukhmer.contactmanagement.api.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.soramitsukhmer.contactmanagement.common.Constants
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class StaffDTO(
        var id : Long,
        var name: String,
        var position: String,
        var company: CompanyDTO,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val createdAt: LocalDateTime,
        @JsonFormat(pattern = Constants.DATETIME_FORMAT)
        val updatedAt: LocalDateTime
)

data class RequestStaffDTO(
        @field:NotEmpty val name: String,
        val position: String?,
        @field:NotNull val company: Long
)