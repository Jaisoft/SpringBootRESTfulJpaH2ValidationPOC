package com.jaisoft.jpa.entity

import javax.persistence.*

@Entity
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        val firstName: String,
        val lastName: String)

