package com.soramitsukhmer.contactmanagement.api.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.soramitsukhmer.contactmanagement.common.Constants
import com.soramitsukhmer.contactmanagement.domain.model.Staff
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class TaskDTO(var id:Long,
                   var name:String,
                   var status:String,
                   var staff: StaffDTO,
                   @JsonFormat(pattern = Constants.DATETIME_FORMAT)
                   var created_at: LocalDateTime,
                   @JsonFormat(pattern = Constants.DATETIME_FORMAT)
                   var updated_at: LocalDateTime
)

data class RequestTaskDTO(
        @field:NotEmpty val name: String,
        @field:NotEmpty val status: String,
        @field:NotNull val staff: Long
)