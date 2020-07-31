package com.soramitsukhmer.contactmanagement.service

import com.soramitsukhmer.contactmanagement.api.exception.FieldNotFoundException
import com.soramitsukhmer.contactmanagement.api.request.LocationDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestLocationDTO
import com.soramitsukhmer.contactmanagement.api.request.RequestStatusDTO
import com.soramitsukhmer.contactmanagement.domain.model.Location
import com.soramitsukhmer.contactmanagement.repository.LocationRepository
import liquibase.pro.packaged.it
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class LocationService (val locationRepository: LocationRepository){

    fun getAllLocation(pageable: Pageable): Page<LocationDTO>{
        return locationRepository.findAll(pageable).map{ it.toDTO() }
    }

    fun getLocation(id:Long):LocationDTO{
        return locationRepository.findById(id).orElseThrow(){
            throw FieldNotFoundException(Location::class.simpleName.toString(), "$id")
        }.toDTO()
    }

    fun createLocation(requestLocationDTO: RequestLocationDTO): LocationDTO
    {
        val newLocation = Location.fromReqDTO((requestLocationDTO))
        return locationRepository.save(newLocation).toDTO()
    }

    fun updateLocation(id:Long, requestLocationDTO: RequestLocationDTO):LocationDTO{
        val updateLocation = locationRepository.findById(id).orElseThrow()
        {
            throw FieldNotFoundException(Location::class.simpleName.toString(), "$id")
        }.updateLocation(requestLocationDTO)

        return locationRepository.save(updateLocation).toDTO()
    }

}