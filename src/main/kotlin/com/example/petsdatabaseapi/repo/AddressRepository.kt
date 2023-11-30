package com.example.petsdatabaseapi.repo

import com.example.petsdatabaseapi.model.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository : JpaRepository<Address, Long> {

    fun findByName(name : String) : Address
}