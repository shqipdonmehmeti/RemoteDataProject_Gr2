package com.example.remotedataproject_gr2.model

data class User(
    val id : Int,
    val name : String,
    val username : String,
    val email : String,
    val address : Address,
    val phone : String,
    val website : String,
    val company : Company
)

data class Address(
    val street : String,
    val geo : Geo
)

data class Geo(
    val lat : Double,
    val lng : Double
)

data class Company(
    val name : String,
)