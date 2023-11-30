package com.example.petsdatabaseapi.controller

import com.example.petsdatabaseapi.model.Address
import com.example.petsdatabaseapi.model.User
import com.example.petsdatabaseapi.repo.AddressRepository
import com.example.petsdatabaseapi.repo.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/users"])
class UserController (
    @Autowired private val userRepository: UserRepository,
    @Autowired private val addressRepository: AddressRepository) {
    @GetMapping("")
    fun getAllUsers(): List<User> =
        userRepository.findAll().toList()

    @Transactional
    @PostMapping("")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {

        val allAddresses = addressRepository.findAll()
        addressRepository.findById(allAddresses.last().id!!).map {
            user.address = it
        }
        val createdUser = userRepository.save(user)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: Long): ResponseEntity<User> {
        val user = userRepository.findById(userId).orElse(null)
        return if (user != null) ResponseEntity(user, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateUserById(@PathVariable("id") userId: Long, @RequestBody user: User): ResponseEntity<User> {

        val existingUser = userRepository.findById(userId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedUser = existingUser.copy(
            username = user.username,
            password = user.password,
            name = user.name,
            surname = user.surname,
            age = user.age,
            roles = user.roles)
        userRepository.save(updatedUser)
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") userId: Long): ResponseEntity<User> {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userRepository.deleteById(userId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}