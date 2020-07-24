package com.soramitsukhmer.contactmanagement.Service

import com.soramitsukhmer.contactmanagement.api.request.CompanyDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestStatusDTO
import com.soramitsukhmer.contactmanagement.repository.CompanyRepository
import com.soramitsukhmer.contactmanagement.service.CompanyService
import com.soramitsukhmer.contactmanagement.service.StatusService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.test.util.AssertionErrors.*

@SpringBootTest
class CompanyServiceTest {

    @Autowired
    lateinit var companyService: CompanyService

    @Autowired
    lateinit var companyRepository: CompanyRepository

    @Autowired
    lateinit var statusService: StatusService

    @BeforeEach
    fun setUp()
    {
//        companyRepository.deleteAll()
        statusService.createStatus(RequestStatusDTO("Active"))
        statusService.createStatus(RequestStatusDTO("Inactive"))
    }

    @Test
    fun testCreateCompany(){
        val createdCompany = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        assertTrue("Created Company Is Not Null",createdCompany.id != null)
        companyService.deleteCompany(createdCompany.id)
    }

    @Test
    fun testUpdateCompany()
    {
        val createdCompany = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        val newUpdateRequest = CompanyServiceTestHelper.validCompanyReqDTO
        newUpdateRequest.name = "New Update"
        val updatedCompany = companyService.updateCompany(createdCompany.id, newUpdateRequest)
        assertNotEquals("Updated Company Name Not Equals With Old Name", createdCompany.name, updatedCompany.name)
        companyService.deleteCompany(createdCompany.id)
    }

    @Test
    fun testListAllCompany()
    {
        var listCompanies = mutableListOf<CompanyDTO>()
        for(value in CompanyServiceTestHelper.listValidCompanyDTO)
            listCompanies.add(companyService.createCompany(value))

        val pageable: Pageable = PageRequest.of(0,10, Sort.by("name").descending())
        val allCompanies = companyService.listCompanies(null, pageable)
        val companies = companyRepository.findAll().toList()

        assertEquals("Get Companies Successfully", allCompanies.pagination.totalElements.toInt(), companies.size)
        for(value in listCompanies)
            companyService.deleteCompany(value.id)
    }

    @Test
    fun testGetCompany()
    {
        val createdCompany = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        val getCompany = companyService.getCompany(createdCompany.id)
        assertEquals("Get Companies Successfully", createdCompany.id, getCompany.id)
        companyService.deleteCompany(createdCompany.id)
    }

    @Test
    fun testDeleteCompany()
    {
        val createdCompany = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        companyService.deleteCompany(createdCompany.id)
        val pageable: Pageable = PageRequest.of(0,10, Sort.by("name").descending())
        val getCompanies = companyService.listCompanies(null, pageable)
        assertFalse("Delete Companies Successfully", getCompanies.content.contains(createdCompany))
    }
}