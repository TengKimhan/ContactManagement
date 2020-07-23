package com.soramitsukhmer.contactmanagement.domain.model

import com.soramitsukhmer.contactmanagement.api.request.CompanyDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestCompanyDTO
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "companies")
data class Company(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id : Long = 0,
        @Column(name = "name")
        var name: String,
        @Column(name = "phone")
        var phone: String,
        @Column(name = "web_url")
        var webUrl: String?,
        @Column(name = "private_pass_phrase")
        var privatePassPhrase: String,
        @CreationTimestamp
        @Column(name = "created_at")
        var createdAt: LocalDateTime = LocalDateTime.now(),
        @UpdateTimestamp
        @Column(name = "updated_at")
        var updatedAt: LocalDateTime = LocalDateTime.now()
){

        @ManyToOne
        @JoinColumn(name = "status_id")
        lateinit var status: Status

        fun toDTO() = CompanyDTO(
                id = id,
                name = name,
                phone = phone,
                webUrl = webUrl,
                createdAt = createdAt,
                updatedAt = updatedAt,
                status = status.toDTO()
        )

        fun updateCompany(reqCompanyDTO: RequestCompanyDTO, status: Status) : Company{
                return this.apply {
                        name = reqCompanyDTO.name
                        phone = reqCompanyDTO.phone
                        webUrl = reqCompanyDTO.webUrl
                        this.status = status
                }
        }

        companion object{
                fun fromReqDTO(reqCompanyDTO: RequestCompanyDTO, status: Status) : Company{
                        return Company(
                                name = reqCompanyDTO.name,
                                phone = reqCompanyDTO.phone,
                                webUrl = reqCompanyDTO.webUrl,
                                privatePassPhrase = "SORA"
                        ).apply { this.status = status }
                }
        }
}