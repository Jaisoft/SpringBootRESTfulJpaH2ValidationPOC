package com.jaisoft.jpa.controller
import com.jaisoft.jpa.entity.UserEntity
import com.jaisoft.jpa.dto.CreateUserDto
import com.jaisoft.jpa.dto.CreatedUserDto
import com.jaisoft.jpa.dto.UpdateUserDto
import com.jaisoft.jpa.dto.UpdatedUserDto
import com.jaisoft.jpa.repository.UserRepository
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
@Api(value = "user", description = "Rest API for user operations", tags = arrayOf("User API"))
class UserController(private val userRepository: UserRepository) {

    @PostMapping("/users")
    fun createUser(@Valid @RequestBody createUserDto: CreateUserDto) :  ResponseEntity<CreatedUserDto>{
       val createdUserEntity =  userRepository.save(UserEntity(0,createUserDto.firstName, createUserDto.lastName))
        val createdUserDto = CreatedUserDto(createdUserEntity.id, createdUserEntity.firstName, createdUserEntity.lastName)
        return if(createdUserEntity != null ){
            ResponseEntity.ok(createdUserDto)
        } else {
            ResponseEntity.notFound().build()
        }

    }

     @GetMapping("/users")
      fun getUsers(): List<CreatedUserDto> {
          return userRepository.findAll().map {
              userEntity ->
              CreatedUserDto(userEntity.id, userEntity.firstName, userEntity.lastName)
          }
      }


      @GetMapping("/users/{id}")
      fun getUserById(@PathVariable id: Long): ResponseEntity<CreatedUserDto> {

          val userEntity: Optional<UserEntity> = userRepository.findById(id)
          val createdUserDto = CreatedUserDto(userEntity.get().id, userEntity.get().firstName, userEntity.get().lastName)
          return if(userEntity != null ){
              ResponseEntity.ok(createdUserDto)
          } else {
              ResponseEntity.notFound().build()
          }
      }

    @PutMapping("/user/{id}")
    fun updateUser(@PathVariable id:Long, @Valid @RequestBody updateUserDto: UpdateUserDto): ResponseEntity<UpdatedUserDto>{
        val userEntity: UserEntity = userRepository.getOne(id)
        userEntity.firstName = updateUserDto.firstName
        userEntity.lastName = updateUserDto.lastName
        val updatedUserEntity : UserEntity = userRepository.save(userEntity)
        val updatedUserDto = UpdatedUserDto(updatedUserEntity.id, updatedUserEntity.firstName, updatedUserEntity.lastName)
        return if(updatedUserEntity != null ){
            ResponseEntity.ok(updatedUserDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}