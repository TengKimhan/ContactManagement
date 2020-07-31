package com.soramitsukhmer.contactmanagement.api.controller

import com.soramitsukhmer.contactmanagement.api.request.CompanyDTO
import com.soramitsukhmer.contactmanagement.api.request.FilterParamCompanyDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestCompanyDTO
import com.soramitsukhmer.contactmanagement.api.response.PageResponse
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
    fun listAllCompanies(@Valid filterParamCompanyDTO: FilterParamCompanyDTO, pageable: Pageable) : ResponseEntity<PageResponse<CompanyDTO>>{
        return ResponseEntity.ok(companyService.listCompanies(filterParamCompanyDTO, pageable))
    }

//    @GetMapping
//    fun listAllCompanies(@RequestParam page: Int = 0, @RequestParam size:Int = 10, @RequestParam sort: String = "createdAt,asc") : ResponseEntity<List<CompanyDTO>>{
//        var sortBy:Sort = Sort.by("createdAt").descending()
//
//        if(sort == "createdAt,asc") sortBy = Sort.by("createdAt").ascending()
//
//        val firstPageWithTwoElements: Pageable = PageRequest.of(page,size, sortBy)
//        return ResponseEntity.ok(companyService.listAllCompanies(firstPageWithTwoElements))
//    }

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
    fun deleteCompany(@PathVariable id: Long) : Any {
        return ok(companyService.deleteCompany(id))
    }

    private fun <T> ok(data: T) : T = data

//    fun deleteCompany(@PathVariable id:Long){
//        ResponseEntity.ok(companyService.deleteCompany(id))
//    }
}