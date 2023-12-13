package com.snapcat.data.model

import java.time.LocalDateTime

data class History(
    val id: String,
    val breed: String,
    val description: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val user: User,
    val userId: String
)