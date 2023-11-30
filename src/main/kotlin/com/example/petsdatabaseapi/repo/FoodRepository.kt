package com.example.petsdatabaseapi.repo

import com.example.petsdatabaseapi.model.Food
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FoodRepository : JpaRepository<Food, Long> {

    fun findFoodByNameFood(name : String) : Food
}