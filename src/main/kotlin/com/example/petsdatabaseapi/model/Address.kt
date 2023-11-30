package com.example.petsdatabaseapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "address")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    @Column(unique = true)
    var name : String = "",
    var currentAddress : String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    var user: Collection<User> = arrayListOf()
)
