package com.coded.spring.entity

import jakarta.persistence.*

@Entity
@Table(name = "profiles")
data class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var firstName: String,
    var lastName: String,
    var phoneNumber: String,

    var userId: Long,
)
