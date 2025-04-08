package com.coded.spring.ordering

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long>

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    val orderID: Long? = null,

    @Column(name = "user")
    val user: String,

    @Column(name = "restaurant")
    val restaurant: String,

    @ElementCollection // Since the specified schema requires a list of items I had to make a seperate table
//     linked by id to the main table
    @CollectionTable(name = "order_items", joinColumns = [JoinColumn(name = "orderID")])
    @Column(name ="item")
    val items: List<String>
){
    constructor() : this(null,"","", emptyList())
}