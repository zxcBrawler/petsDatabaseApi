package com.example.petsdatabaseapi.controller

import com.example.petsdatabaseapi.model.Country
import com.example.petsdatabaseapi.repo.CountryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/country"])
class CountryController (@Autowired private val countryRepository: CountryRepository) {
    @GetMapping("")
    fun getAllCountry(): List<Country> =
        countryRepository.findAll().toList()

    @PostMapping("")
    fun createCountry(@RequestBody country: Country): ResponseEntity<Country> {
        val createdCountry = countryRepository.save(country)
        return ResponseEntity(createdCountry, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getCountryById(@PathVariable("id") countryId: Long): ResponseEntity<Country> {
        val country = countryRepository.findById(countryId).orElse(null)
        return if (country != null) ResponseEntity(country, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateCountryById(@PathVariable("id") countryId: Long, @RequestBody country: Country): ResponseEntity<Country> {

        val existingCountry = countryRepository.findById(countryId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedCountry = existingCountry.copy(
            name = country.name,
            dateFormed = country.dateFormed,
            continent = country.continent,
        )
        countryRepository.save(updatedCountry)
        return ResponseEntity(updatedCountry, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteCountryById(@PathVariable("id") countryId: Long): ResponseEntity<Country> {
        if (!countryRepository.existsById(countryId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        countryRepository.deleteById(countryId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}