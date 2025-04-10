package com.coded.spring.entity

import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne // this is for fk
    val user: UserEntity,

    @Column(name = "restaurant")
    var restaurant: String,

    @OneToMany(mappedBy = "id")
    val items: MutableList<ItemEntity>

)