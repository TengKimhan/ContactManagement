package com.soramitsukhmer.contactmanagement.Service

import com.github.javafaker.Faker
import com.soramitsukhmer.contactmanagement.api.request.RequestCompanyDTO

object CompanyServiceTestHelper {

    var faker = Faker()

    val validCompanyReqDTO = RequestCompanyDTO(
            name = faker.name().fullName(),
            phone = faker.phoneNumber().cellPhone(),
            webUrl = faker.internet().url())

    val listValidCompanyDTO = listOf(RequestCompanyDTO(name = faker.name().fullName(), phone = faker.phoneNumber().cellPhone(), webUrl = faker.internet().url()),
            RequestCompanyDTO(name = faker.name().fullName(), phone = faker.phoneNumber().cellPhone(), webUrl = faker.internet().url()),
            RequestCompanyDTO(name = faker.name().fullName(), phone = faker.phoneNumber().cellPhone(), webUrl = faker.internet().url()))

}