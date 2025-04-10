package com.coded.spring.ordering

import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Named
interface OrderRepository : JpaRepository<Order, Long>

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    var orderID: Long? = null,
    // Thanks to Mohammed Sheshter he figured out we need to use back ticks to resolve the 500 server error caused by JPA getting confused by "users"
//    @Column(name = "`user`")
    @Column(name = "name")
    var user: String = "",

    var restaurant: String = "",

    // Since the specified schema requires a list of items I had to make a seperate table
    @CollectionTable(name = "items")
    @JoinColumn()
    var items: MutableList<String?> = mutableListOf()
){
    constructor() : this(null,"","", mutableListOf())
}