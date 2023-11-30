package com.example.petsdatabaseapi.controller

import com.example.petsdatabaseapi.model.Pet
import com.example.petsdatabaseapi.repo.PetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/pet"])
class PetController (@Autowired private val petRepository: PetRepository) {
    @GetMapping("")
    fun getAllPet(): List<Pet> =
        petRepository.findAll().toList()

    @PostMapping("")
    fun createPet(@RequestBody pet: Pet): ResponseEntity<Pet> {
        val createdPet = petRepository.save(pet)
        return ResponseEntity(createdPet, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getPetById(@PathVariable("id") petId: Long): ResponseEntity<Pet> {
        val pet = petRepository.findById(petId).orElse(null)
        return if (pet != null) ResponseEntity(pet, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updatePetById(@PathVariable("id") petId: Long, @RequestBody pet: Pet): ResponseEntity<Pet> {

        val existingPet = petRepository.findById(petId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedPet = existingPet.copy(
            name = pet.name,
            age = pet.age,
            weight = pet.weight,
            typePet = pet.typePet
        )
        petRepository.save(updatedPet)
        return ResponseEntity(updatedPet, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deletePetById(@PathVariable("id") petId: Long): ResponseEntity<Pet> {
        if (!petRepository.existsById(petId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        petRepository.deleteById(petId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}