package com.example.petsdatabaseapi.repo

import com.example.petsdatabaseapi.model.Country
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository : JpaRepository<Country, Long> {
}