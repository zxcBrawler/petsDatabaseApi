package com.example.petsdatabaseapi.controller

import com.example.petsdatabaseapi.model.Pet
import com.example.petsdatabaseapi.model.TypePet
import com.example.petsdatabaseapi.model.User
import com.example.petsdatabaseapi.repo.TypePetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/typePet"])
class TypePetController (@Autowired private val typePetRepository: TypePetRepository) {

    @GetMapping("")
    fun getAllTypePet(): List<TypePet> =
        typePetRepository.findAll().toList()

    @PostMapping("")
    fun createTypePet(@RequestBody typePet: TypePet): ResponseEntity<TypePet> {
        val createdTypePet = typePetRepository.save(typePet)
        return ResponseEntity(createdTypePet, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getTypePetById(@PathVariable("id") typePetId: Long): ResponseEntity<TypePet> {
        val typePet = typePetRepository.findById(typePetId).orElse(null)
        return if (typePet != null) ResponseEntity(typePet, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateTypePetById(@PathVariable("id") typePetId: Long, @RequestBody typePet: TypePet): ResponseEntity<TypePet> {

        val existingTypePet = typePetRepository.findById(typePetId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedTypePet = existingTypePet.copy(
            name = typePet.name,
           )
        typePetRepository.save(updatedTypePet)
        return ResponseEntity(updatedTypePet, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteTypePetById(@PathVariable("id") typePetId: Long): ResponseEntity<TypePet> {
        if (!typePetRepository.existsById(typePetId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        typePetRepository.deleteById(typePetId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}