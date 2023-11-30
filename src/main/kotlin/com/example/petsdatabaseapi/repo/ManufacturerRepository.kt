package com.example.petsdatabaseapi.repo

import com.example.petsdatabaseapi.model.Manufacturer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ManufacturerRepository : JpaRepository<Manufacturer, Long> {
}