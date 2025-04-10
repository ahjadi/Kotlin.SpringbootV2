package com.coded.spring.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "items")
class ItemEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    var quantity: Int,

    var price :   BigDecimal)



