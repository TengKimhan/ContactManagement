package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.LocationDTO
import javax.persistence.*


@Entity
@Table(name = "company_location")
data class CompanyLocation(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long = 0
) {

    @ManyToOne
    @JoinColumn(name = "location_id")
    lateinit var location: Location

    @ManyToOne
    @JoinColumn(name = "company_id")
    lateinit var company: Company

    fun toLocationDTO() = LocationDTO(
            id = this.location.id,
            name = this.location.name
    )
}