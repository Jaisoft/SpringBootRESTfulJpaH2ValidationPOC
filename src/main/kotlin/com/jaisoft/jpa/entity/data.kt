package com.jaisoft.jpa.entity

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,
        @get: NotBlank
        var firstName: String,
        @get: NotBlank
        var lastName: String)

