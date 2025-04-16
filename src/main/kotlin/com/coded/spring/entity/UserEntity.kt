package com.coded.spring.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "name")
    var name: String,
    @Column(name = "email")
    var email: String,

    val username: String,
    val password: String
//ALTER TABLE users ADD username varchar(255) unique
//ALTER TABLE users ADD password varchar(255)
)
{
    constructor() : this(null,"","", "", "")
}