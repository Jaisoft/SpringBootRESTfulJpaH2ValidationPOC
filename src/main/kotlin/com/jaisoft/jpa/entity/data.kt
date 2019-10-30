package com.jaisoft.jpa.entity

import javax.persistence.*

@Entity
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        var firstName: String,
        var lastName: String)

