package com.example.petsdatabaseapi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    @Column(nullable = false, length = 25)
    var name : String = "",
    @Column(nullable = false, length = 35)
    var surname : String = "",
    @Column(unique = true, length = 40)
    var username : String = "",
    @Column(nullable = false, length = 3)
    var age : Int = 0,

    @Column(nullable = false)
    var password : String = "",


    @ElementCollection(targetClass = TypeRole::class, fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")]
    ) @Enumerated(EnumType.STRING)
    var roles: MutableSet<TypeRole>? = null,

    @ManyToOne
    @JoinColumn(name = "address_id")
    var address: Address = Address(),


    @ManyToMany
    @JoinTable(
    name = "user_pets",
    joinColumns = [JoinColumn(name = "user_id")],
    inverseJoinColumns = [JoinColumn(name = "pet_id")])
    val pet: List<Pet> = arrayListOf(),
)
