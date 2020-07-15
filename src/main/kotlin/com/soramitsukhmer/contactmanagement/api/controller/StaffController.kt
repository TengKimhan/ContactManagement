package com.soramitsukhmer.contactmanagement.api.controller

import com.soramitsukhmer.contactmanagement.api.request.RequestStaffDTO
import com.soramitsukhmer.contactmanagement.api.request.StaffDTO
import com.soramitsukhmer.contactmanagement.service.StaffService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/api/v1/staffs"])
class StaffController(
        val staffService: StaffService
){
    @GetMapping
    fun listAllStaffs() : ResponseEntity<List<StaffDTO>>{
        return ResponseEntity.ok(staffService.listAllStaffs())
    }

    @GetMapping("/{id}")
    fun getStaff(@PathVariable id : Long) : ResponseEntity<StaffDTO>{
        return ResponseEntity.ok(staffService.getStaff(id))
    }

    @PostMapping
    fun createStaff(@Valid @RequestBody reqStaffDTO: RequestStaffDTO) : ResponseEntity<StaffDTO>{
        return ResponseEntity.ok(staffService.createStaff(reqStaffDTO))
    }

    @PutMapping("/{id}")
    fun updateStaff(@PathVariable id: Long, @Valid @RequestBody reqStaffDTO: RequestStaffDTO): ResponseEntity<StaffDTO>{
        return ResponseEntity.ok(staffService.updateStaff(id, reqStaffDTO))
    }

    @DeleteMapping("/{id}")
    fun deleteStaff(@PathVariable id: Long) : ResponseEntity<String>{
        return ResponseEntity.ok(staffService.deleteStaff(id))
    }
}