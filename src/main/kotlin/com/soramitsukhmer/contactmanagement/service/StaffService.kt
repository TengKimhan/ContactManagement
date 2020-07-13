package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import com.soramitsukhmer.contactmanagement.repository.StaffRepository
import org.springframework.stereotype.Service

@Service
class StaffService(
        val staffRepository: StaffRepository,
        val companyRepository: CompanyRepository
){
}