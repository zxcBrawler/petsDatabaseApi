package com.example.petsdatabaseapi.repo


import com.example.petsdatabaseapi.model.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : JpaRepository<Pet, Long> {

    fun findPetByName(name : String) : Pet
}