package com.example.petsdatabaseapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PetsDatabaseApiApplication

fun main(args: Array<String>) {
    runApplication<PetsDatabaseApiApplication>(*args)
}
