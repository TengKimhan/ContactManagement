package com.soramitsukhmer.contactmanagement.Service

import com.github.javafaker.Faker
import com.soramitsukhmer.contactmanagement.api.request.RequestStaffDTO

object StaffServiceTestHelper {

    var faker = Faker()

    val validStaffDTO = RequestStaffDTO(
            name = faker.name().fullName(),
            gender = faker.random().toString(),
            location = faker.address().city(),
            position = faker.job().position(),
            company = 1
    )

    val listValidStaffDTO = listOf(
            RequestStaffDTO(name = faker.name().fullName(),
                    gender = faker.options().toString(),
                    location = faker.address().fullAddress(),
                    position = faker.job().position(),
                    company = 1),
            RequestStaffDTO(name = faker.name().fullName(),
                    gender = faker.options().toString(),
                    location = faker.address().fullAddress(),
                    position = faker.job().position(),
                    company = 1),
            RequestStaffDTO(name = faker.name().fullName(),
                    gender = faker.options().toString(),
                    location = faker.address().fullAddress(),
                    position = faker.job().position(),
                    company = 1)
    )
}