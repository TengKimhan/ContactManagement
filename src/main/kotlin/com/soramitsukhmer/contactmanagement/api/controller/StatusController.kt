package com.soramitsukhmer.contactmanagement.api.controller

import com.soramitsukhmer.contactmanagement.api.request.RequestStatusDTO
import com.soramitsukhmer.contactmanagement.api.request.StatusDTO
import com.soramitsukhmer.contactmanagement.service.StatusService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/api/v1/status"])
class StatusController(val statusService: StatusService) {

//    @GetMapping
//    fun getAllStatus(pageable: Pageable):ResponseEntity<Page<StatusDTO>>
//    {
//        return ResponseEntity.ok(statusService.getAllStatus(pageable))
//    }
//
//    @GetMapping("/{id}")
//    fun getStatus(@PathVariable("id") id:Long):ResponseEntity<StatusDTO>
//    {
//        return ResponseEntity.ok(statusService.getStatus(id))
//    }
//
//    @PostMapping
//    fun createStatus(@Valid @RequestBody requestStatusDTO: RequestStatusDTO): ResponseEntity<StatusDTO>
//    {
//        return ResponseEntity.ok(statusService.createStatus(requestStatusDTO))
//    }
//
//    @PutMapping("{id}")
//    fun updateStatus(@Valid @RequestBody requestStatusDTO: RequestStatusDTO, @PathVariable("id") id:Long): ResponseEntity<StatusDTO>
//    {
//        return ResponseEntity.ok(statusService.updateStatus(id,requestStatusDTO))
//    }
}