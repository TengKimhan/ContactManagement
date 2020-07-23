package com.soramitsukhmer.contactmanagement.domain.spec

import com.soramitsukhmer.contactmanagement.domain.model.Company
import com.soramitsukhmer.contactmanagement.domain.model.Status
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.JoinType

class CompanySpec {

    companion object{
        fun genSearchSpec(value: String): Specification<Company>?{
            return Specification {root, _, criteriaBuilder ->
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(Company::name.name)), "%$value%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(Company::phone.name)), "%$value%")
                )
            }
        }

        fun genFilterStatus(statusID : Long) : Specification<Company>? {
            return Specification { root, _, criteriaBuilder ->
                val status = root.join<Company, Status>(Company::status.name, JoinType.INNER)
                criteriaBuilder.equal(status.get<Long>(Status::id.name), statusID)
            }
        }
    }
}