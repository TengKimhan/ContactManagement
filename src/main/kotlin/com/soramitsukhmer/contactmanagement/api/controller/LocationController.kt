package com.soramitsukhmer.contactmanagement.api.controller

import com.soramitsukhmer.contactmanagement.api.request.LocationDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestLocationDTO
import com.soramitsukhmer.contactmanagement.service.CompanyService
import com.soramitsukhmer.contactmanagement.service.LocationService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.validation.Valid


@RestController
@RequestMapping(path = ["/api/v1/location"])
class LocationController (
        val locationService: LocationService
) {
    @GetMapping
    fun listAllLocation(pageable: Pageable): ResponseEntity<Page<LocationDTO>> {
        return ResponseEntity.ok(locationService.getAllLocation(pageable))
    }

    @GetMapping("/{id}")
    fun getLocation(@PathVariable("id") id: Long): ResponseEntity<LocationDTO> {
        return ResponseEntity.ok(locationService.getLocation(id))
    }

    @PostMapping
    fun createLocation(@Valid @RequestBody requestLocationDTO: RequestLocationDTO): ResponseEntity<LocationDTO> {
        return ResponseEntity.ok(locationService.createLocation(requestLocationDTO))
    }

    @PutMapping("{id}")
    fun updateLocation(@Valid @RequestBody requestLocationDTO: RequestLocationDTO, @PathVariable("id") id: Long): ResponseEntity<LocationDTO>
    {
        return ResponseEntity.ok(locationService.updateLocation(id,requestLocationDTO))
    }
}