package com.example.petsdatabaseapi.repo

import com.example.petsdatabaseapi.model.TypePet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypePetRepository : JpaRepository<TypePet, Long> {
    fun findTypePetByName(name : String) : TypePet
}