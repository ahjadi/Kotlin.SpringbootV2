package com.coded.spring.ordering

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface UsersRepository : JpaRepository<User, Long>

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var orderID: Long? = null,

    @Column(name = "name")
    var user: String = "",
    @Column(name = "age")
    var age: Int? = null

){
    constructor() : this(null, "", null)
}