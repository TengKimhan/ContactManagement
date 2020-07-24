package com.soramitsukhmer.contactmanagement.api.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.soramitsukhmer.contactmanagement.common.Constants
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class StatusDTO(
        val id: Long,
        val name: String
)

data class RequestStatusDTO(
        @field:NotEmpty val name: String
)