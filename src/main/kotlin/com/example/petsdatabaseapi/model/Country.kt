package com.example.petsdatabaseapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "country")
data class Country(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    @Column(nullable = false, length = 25)
    var name : String = "",
    @Column(nullable = false, length = 4)
    var dateFormed : Int = 0,
    @Column(nullable = false, length = 50)
    var continent : String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    var manufacturer: Collection<Manufacturer> = arrayListOf()
)
