package com.jaisoft.jpa.repository

import com.jaisoft.jpa.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long>



