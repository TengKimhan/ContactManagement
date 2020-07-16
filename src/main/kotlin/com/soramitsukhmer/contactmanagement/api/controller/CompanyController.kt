package com.soramitsukhmer.contactmanagement.api.controller

import com.soramitsukhmer.contactmanagement.api.request.CompanyDTO
import com.soramitsukhmer.contactmanagement.api.request.FilterParamsStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestCompanyDTO
import com.soramitsukhmer.contactmanagement.service.CompanyService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/api/v1/companies"])
class CompanyController(
        val companyService: CompanyService
){
    @GetMapping
    fun listAllCompanies() : ResponseEntity<List<CompanyDTO>>{
        return ResponseEntity.ok(companyService.listAllCompanies())
    }

    @GetMapping("/{id}")
    fun getCompany(@PathVariable id: Long) : ResponseEntity<CompanyDTO>{
        return ResponseEntity.ok(companyService.getCompany(id))
    }

    @PostMapping
    fun createCompany(@Valid @RequestBody requestCompanyDTO: RequestCompanyDTO) : ResponseEntity<CompanyDTO>{
        return ResponseEntity.ok(companyService.createCompany(requestCompanyDTO))
    }

    @PutMapping("/{id}")
    fun updateCompany(@PathVariable id: Long, @Valid @RequestBody requestCompanyDTO: RequestCompanyDTO) : ResponseEntity<CompanyDTO>{
        return ResponseEntity.ok(companyService.updateCompany(id, requestCompanyDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteCompany(@PathVariable id: Long) : ResponseEntity<String>{
        return ResponseEntity.ok(companyService.deleteCompany(id))
    }
}