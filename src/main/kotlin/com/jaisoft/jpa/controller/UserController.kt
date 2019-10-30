package com.jaisoft.jpa.controller
import com.jaisoft.jpa.entity.UserEntity
import com.jaisoft.jpa.dto.CreateUserDto
import com.jaisoft.jpa.dto.CreatedUserDto
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
    fun createNewBeer(@Valid @RequestBody createUserDto: CreateUserDto) :  ResponseEntity<CreatedUserDto>{
       val createdUserEntity =  userRepository.save(UserEntity(0,createUserDto.firstName, createUserDto.lastName))
        val createdUserDto = CreatedUserDto(createdUserEntity.id, createdUserEntity.firstName, createdUserEntity.lastName)
        return if(createdUserEntity != null ){
            ResponseEntity.ok(createdUserDto)
        } else {
            ResponseEntity.notFound().build()
        }

    }

     @GetMapping("/users")
      fun getBeers(): List<CreatedUserDto> {
          return userRepository.findAll().map {
              userEntity ->
              CreatedUserDto(userEntity.id, userEntity.firstName, userEntity.lastName)
          }
      }


      @GetMapping("/users/{id}")
      fun getBeerById(@PathVariable id: Long): ResponseEntity<CreatedUserDto> {

          val userEntity: Optional<UserEntity> = userRepository.findById(id)
            val createdUserDto = CreatedUserDto(userEntity.get().id, userEntity.get().firstName, userEntity.get().lastName)
          return if(userEntity != null ){
              ResponseEntity.ok(createdUserDto)
          } else {
              ResponseEntity.notFound().build()
          }
      }



    /*@PutMapping("/beer/{id}")
    fun updateBeer(@PathVariable id:Long, @Valid @RequestBody beerDto: CreatedUserDto): ResponseEntity<BeerDto>{

        var beerData: BeerData = userRepository.getOne(id)

        val updatedBeerData:BeerData = beerData.copy(beerName = beerDto.beerName, beerColour = beerDto.beerColour, alcoholPercentage = beerDto.alcoholPercentage)

        val savedBeerData : BeerData = userRepository.save(updatedBeerData)
        val beerDto = BeerDto(savedBeerData.id, savedBeerData.beerName, savedBeerData.alcoholPercentage, savedBeerData.beerColour, savedBeerData.beerType.id, savedBeerData.beerType.beerType, savedBeerData.beerType.beerJudgeCertification, savedBeerData.brewery.id, savedBeerData.brewery.breweryName)

        return if(beerDto != null ){
            ResponseEntity.ok(beerDto)
        } else {
            ResponseEntity.notFound().build()
        }

    }*/

}