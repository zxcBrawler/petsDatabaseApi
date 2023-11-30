package com.example.petsdatabaseapi.controller

import com.example.petsdatabaseapi.model.Food
import com.example.petsdatabaseapi.repo.FoodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/food"])
class FoodController(@Autowired private val foodRepository: FoodRepository) {
    @GetMapping("")
    fun getAllFood(): List<Food> =
        foodRepository.findAll().toList()

    @PostMapping("")
    fun createFood(@RequestBody food: Food): ResponseEntity<Food> {
        val createdFood = foodRepository.save(food)
        return ResponseEntity(createdFood, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getFoodById(@PathVariable("id") foodId: Long): ResponseEntity<Food> {
        val food = foodRepository.findById(foodId).orElse(null)
        return if (food != null) ResponseEntity(food, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateFoodById(@PathVariable("id") foodId: Long, @RequestBody food: Food): ResponseEntity<Food> {

        val existingFood = foodRepository.findById(foodId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedFood = existingFood.copy(
            nameFood = food.nameFood,
            mainIngredient = food.mainIngredient,
            manufacturer = food.manufacturer,

        )
        foodRepository.save(updatedFood)
        return ResponseEntity(updatedFood, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteFoodById(@PathVariable("id") foodId: Long): ResponseEntity<Food> {
        if (!foodRepository.existsById(foodId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        foodRepository.deleteById(foodId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}