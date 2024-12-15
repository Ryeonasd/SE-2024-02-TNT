package TNT.SE_2024_02_TNT.controller

import TNT.SE_2024_02_TNT.dto.UserDtoRequest
import TNT.SE_2024_02_TNT.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController(var userService: UserService) {

    @PostMapping(value = ["/register"])
    fun registerUser(@RequestBody userDtoRequest: UserDtoRequest): ResponseEntity<String> {
        return try {
            ResponseEntity(userService.registerUser(userDtoRequest), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping(value = ["/login"])
    fun loginUser(@RequestBody userDtoRequest: UserDtoRequest): ResponseEntity<String> {
        return try {
            ResponseEntity(userService.login(userDtoRequest), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }
}