package com.soramitsukhmer.contactmanagement.domain.spec

import com.soramitsukhmer.contactmanagement.domain.model.Company
import com.soramitsukhmer.contactmanagement.domain.model.Staff
import org.springframework.data.jpa.domain.Specification
import java.time.LocalDateTime
import javax.persistence.criteria.JoinType

class StaffSpec {
    companion object{
        fun genSearchSpec(value : String) : Specification<Staff>?{
            return Specification { root, _, criteriaBuilder ->
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(Staff::name.name)), "%$value%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(Staff::gender.name)), "%$value%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(Staff::location.name)), "%$value%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get(Staff::position.name)), "%$value%")
                )
            }
        }

        fun genFilterCompany(companyId : Long) : Specification<Staff>? {
            return Specification { root, _, criteriaBuilder ->
                val company = root.join<Staff, Company>(Staff::company.name, JoinType.INNER)
                criteriaBuilder.equal(company.get<Long>(Company::id.name), companyId)
            }
        }
    }
}