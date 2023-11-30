package com.example.petsdatabaseapi.controller

import com.example.petsdatabaseapi.model.Address
import com.example.petsdatabaseapi.repo.AddressRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/address"])
class AddressController(@Autowired private val addressRepository: AddressRepository) {
    @GetMapping("")
    fun getAllAddress(): List<Address> =
        addressRepository.findAll().toList()

    @PostMapping("")
    fun createAddress(@RequestBody address: Address): ResponseEntity<Address> {
        val createdAddress = addressRepository.save(address)
        return ResponseEntity(createdAddress, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getAddressById(@PathVariable("id") addressId: Long): ResponseEntity<Address> {
        val address = addressRepository.findById(addressId).orElse(null)
        return if (address != null) ResponseEntity(address, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateAddressById(@PathVariable("id") addressId: Long, @RequestBody address: Address)
    : ResponseEntity<Address> {

        val existingAddress = addressRepository.findById(addressId).orElse(null)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val updatedAddress = existingAddress.copy(
            name = address.name,
            currentAddress = address.currentAddress,
        )
        addressRepository.save(updatedAddress)
        return ResponseEntity(updatedAddress, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteAddressById(@PathVariable("id") addressId: Long): ResponseEntity<Address> {
        if (!addressRepository.existsById(addressId)) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        addressRepository.deleteById(addressId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}