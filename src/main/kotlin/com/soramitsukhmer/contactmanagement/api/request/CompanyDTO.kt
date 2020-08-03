package com.soramitsukhmer.contactmanagement.api.request

import com.fasterxml.jackson.annotation.JsonFormat
import com.soramitsukhmer.contactmanagement.common.Constants
import com.soramitsukhmer.contactmanagement.domain.model.Location
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
        val updatedAt: LocalDateTime,
        val location: List<LocationDTO>
)

data class FilterParamCompanyDTO(
        val q: String?,
        val statusId:Long?
)

data class RequestCompanyDTO(
        @field:NotEmpty var name: String,
        @field:NotEmpty var phone: String,
        var webUrl: String?,
        @field:NotNull var status: Long = 1,
        @field:NotNull var locations: List<Long>
)

data class RequestCompanyWithStaffsDTO(
        @field:NotEmpty var name: String,
        @field:NotEmpty var phone: String,
        var webUrl: String?,
        @field:NotNull var status: Long = 1,
        @field:NotNull var staffs: List<RequestStaffDTO>,
        @field:NotNull var location: List<Long>

)