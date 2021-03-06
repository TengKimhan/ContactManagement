package com.soramitsukhmer.contactmanagement.api.request

import com.soramitsukhmer.contactmanagement.domain.model.Company
import javax.validation.constraints.NotEmpty

data class LocationDTO(
        val id: Long,
        val name: String
)

data class RequestLocationDTO(
        @field:NotEmpty val  name: String
)