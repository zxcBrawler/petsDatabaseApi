package com.example.petsdatabaseapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "food")
data class Food(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    @Column(nullable = false, length = 25)
    var nameFood : String = "",
    @Column(nullable = false, length = 100)
    var mainIngredient : String = "",

    @JsonIgnore
    @ManyToOne
    val manufacturer : Manufacturer = Manufacturer(),

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "pet_food",
        joinColumns = [JoinColumn(name = "pet_id")],
        inverseJoinColumns = [JoinColumn(name = "food_id")])
    var pet: List<Pet> = arrayListOf()

)
