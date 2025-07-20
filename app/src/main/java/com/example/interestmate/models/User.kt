package com.example.interestmate.models

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val interests: List<String> = emptyList()
)
