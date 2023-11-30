package com.example.petsdatabaseapi.repo


import com.example.petsdatabaseapi.model.User
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

   fun findByUsername(@Param("username") username : String) : User

   fun findByAddressId(addressId : Long) : List<User>
}