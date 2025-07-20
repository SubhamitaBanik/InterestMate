package com.example.interestmate.models

data class InterestGroup(
    val groupName: String = "",
    val description: String = "",
    val members: Map<String, Boolean> = emptyMap()
)

