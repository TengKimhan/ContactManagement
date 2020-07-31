package com.soramitsukhmer.contactmanagement.domain.model
import com.soramitsukhmer.contactmanagement.api.request.LocationDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestLocationDTO
import javax.persistence.*

@Entity
@Table(name = "location")
data class Location(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long = 0,
        @Column(name = "name")
        var name : String
) {
        @ManyToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinTable(name = "company_location", joinColumns = arrayOf(JoinColumn(name = "location_id", referencedColumnName = "id")), inverseJoinColumns = arrayOf(JoinColumn(name = "company_id", referencedColumnName = "id")))
        lateinit var companies: Set<Company>


//        @OneToMany(mappedBy = "location", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
//        lateinit var companyLocation: Set<CompanyLocation>


//        @ManyToMany(cascade = arrayOf(CascadeType.ALL))
//        @JoinTable(name = "company_location", joinColumns = arrayOf(JoinColumn(name = "location_id", referencedColumnName = "id")), inverseJoinColumns = arrayOf(JoinColumn(name = "company_id", referencedColumnName = "id")))
//        var companies: List<Company> = mutableListOf<Company>()

//        @OneToMany(mappedBy = "location")
//        lateinit var companyLocation: Set<CompanyLocation>

        fun toDTO() = LocationDTO(
                id = id,
                name = name,
                companies = companies
        )

        fun updateLocation(requestLocationDTO: RequestLocationDTO) : Location
        {
                return this.apply { name = requestLocationDTO.name }
        }

        companion object{
                fun fromReqDTO(requestLocationDTO: RequestLocationDTO) : Location
                {
                        return Location(
                                name = requestLocationDTO.name
                        )
                }
        }

//        @ManyToMany
//        @JoinColumn(name = "company_id")
//        lateinit var company: Company
}