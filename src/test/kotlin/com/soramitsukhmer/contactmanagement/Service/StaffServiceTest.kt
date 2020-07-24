package com.soramitsukhmer.contactmanagement.Service

import com.soramitsukhmer.contactmanagement.api.request.RequestStatusDTO
import com.soramitsukhmer.contactmanagement.api.request.StaffDTO
import com.soramitsukhmer.contactmanagement.repository.StaffRepository
import com.soramitsukhmer.contactmanagement.service.CompanyService
import com.soramitsukhmer.contactmanagement.service.StaffService
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
class StaffServiceTest {

    @Autowired
    lateinit var staffService:StaffService

    @Autowired
    lateinit var staffRepository: StaffRepository

    @Autowired
    lateinit var statusService: StatusService

    @Autowired
    lateinit var companyService: CompanyService

    @BeforeEach
    fun run(){
//        staffRepository.deleteAll()
        statusService.createStatus(RequestStatusDTO("Active"))
        statusService.createStatus(RequestStatusDTO("Inactive"))
    }

    @Test
    fun testCreateStaff()
    {
        val company = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        val validStaff = StaffServiceTestHelper.validStaffDTO
        validStaff.company = company.id

        val createdStaff = staffService.createStaff(validStaff)
        assertNotNull("Staff is created", createdStaff.id)
        staffService.deleteStaff(createdStaff.id)
    }

    @Test
    fun testUpdateStaff()
    {
        val company = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        val validStaff = StaffServiceTestHelper.validStaffDTO
        validStaff.company = company.id

        val createdStaff = staffService.createStaff(validStaff)
        val updatedStaff = StaffServiceTestHelper.validStaffDTO
        updatedStaff.name = "saklfjklsdjf"
        val staff = staffService.updateStaff(createdStaff.id, updatedStaff)
        assertNotEquals("New name and old name is not the same", createdStaff.name, staff.name)
        staffService.deleteStaff(createdStaff.id)
    }

    @Test
    fun testListAllStaff()
    {
        val company = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        var listStaff = mutableListOf<StaffDTO>()
        for(value in StaffServiceTestHelper.listValidStaffDTO){
            value.company = company.id
            listStaff.add(staffService.createStaff(value))
        }

        val pageable: Pageable = PageRequest.of(0,10, Sort.by("name").descending())
        val allStaffs = staffService.listAllStaffs(null, pageable)
        val staffs = staffRepository.findAll().toList()

        assertEquals("Get all staffs successfully", allStaffs.pagination.totalElements.toInt(),staffs.size)
        for(value in listStaff)
            staffService.deleteStaff(value.id)
    }

    @Test
    fun testGetStaff()
    {
        val company = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        val validStaff = StaffServiceTestHelper.validStaffDTO
        validStaff.company = company.id

        val createdStaff = staffService.createStaff(validStaff)
        val getStaff = staffService.getStaff(createdStaff.id)
        assertEquals("Get staff successfully", createdStaff.id, getStaff.id)
        staffService.deleteStaff(createdStaff.id)
    }

    @Test
    fun testDeleteStaff()
    {
        val company = companyService.createCompany(CompanyServiceTestHelper.validCompanyReqDTO)
        val validStaff = StaffServiceTestHelper.validStaffDTO
        validStaff.company = company.id

        val createdStaff = staffService.createStaff(validStaff)
        staffService.deleteStaff(createdStaff.id)
        val pageable: Pageable = PageRequest.of(0,10, Sort.by("name").descending())
        val staffs = staffService.listAllStaffs(null, pageable)
        assertFalse("Delete staff successfully", staffs.content.contains(createdStaff))
    }
}