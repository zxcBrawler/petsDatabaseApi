package com.example.petsdatabaseapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "pet")
data class Pet(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long = 0,
    @Column(nullable = false, length = 25)
    var name : String = "",
    @Column(nullable = false, length = 3)
    var age : Int = 0,

    @Column(nullable = false, length = 5)
    var weight : Double = 0.0,

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "typePet_id")
    var typePet: TypePet = TypePet(),

    @JsonIgnore
    @ManyToMany
    @JoinTable(
    name = "user_pets",
    joinColumns = [JoinColumn(name = "pet_id")],
    inverseJoinColumns = [JoinColumn(name = "user_id")])
    val user: List<User> = arrayListOf(),

    @JsonIgnore
    @ManyToMany
    @JoinTable(
    name = "pet_food",
    joinColumns = [JoinColumn(name = "food_id")],
    inverseJoinColumns = [JoinColumn(name = "pet_id")])
    var food: List<Food> = arrayListOf()
)
