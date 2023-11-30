package com.example.petsdatabaseapi.controller

import com.example.petsdatabaseapi.model.Manufacturer
import com.example.petsdatabaseapi.repo.ManufacturerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/manufacturer"])
class ManufacturerController (@Autowired private val manufacturerRepository: ManufacturerRepository) {
    @GetMapping("")
    fun getAllManufacturer(): List<Manufacturer> =
        manufacturerRepository.findAll().toList()

    @PostMapping("")
    fun createManufacturer(@RequestBody manufacturer: Manufacturer): ResponseEntity<Manufacturer> {
        val createdManufacturer = manufacturerRepository.save(manufacturer)
        return ResponseEntity(createdManufacturer, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getManufacturerById(@PathVariable("id") manufacturerId: Long): ResponseEntity<Manufacturer> {
        val manufacturer = manufacturerRepository.findById(manufacturerId).orElse(null)
        return if (manufacturer != null) ResponseEntity(manufacturer, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateManufacturerById(@PathVariable("id") manufacturerId: Long, @RequestBody manufacturer: Manufacturer): ResponseEntity<Manufacturer> {

        val existingManufacturer = manufacturerRepository.findById(manufacturerId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedManufacturer = existingManufacturer.copy(
            name = manufacturer.name,
            address = manufacturer.address,
            yearFormed = manufacturer.yearFormed,
            country = manufacturer.country

            )
        manufacturerRepository.save(updatedManufacturer)
        return ResponseEntity(updatedManufacturer, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteManufacturerById(@PathVariable("id") manufacturerId: Long): ResponseEntity<Manufacturer> {
        if (!manufacturerRepository.existsById(manufacturerId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        manufacturerRepository.deleteById(manufacturerId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}