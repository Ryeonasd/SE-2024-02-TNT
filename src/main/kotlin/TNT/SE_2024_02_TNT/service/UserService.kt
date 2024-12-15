package TNT.SE_2024_02_TNT.service

import TNT.SE_2024_02_TNT.dto.UserDtoRequest
import TNT.SE_2024_02_TNT.entity.User
import TNT.SE_2024_02_TNT.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Transactional
@Service
class UserService(var userRepository: UserRepository) {

    fun registerUser(userDtoRequest: UserDtoRequest): String {
        if (userDtoRequest.id == "" || userDtoRequest.password == "") {
            throw Exception("아이디 혹은 비밀번호를 입력하세요.")
        }

        var user: User? = userRepository.findByUserId(userDtoRequest.id)
        if (user != null) {
            throw Exception("이미 사용중인 ID입니다.")
        }

        user = User(userDtoRequest.id, userDtoRequest.password, null)
        userRepository.save(user)
        return "회원 가입 성공!"
    }

    fun login(userDtoRequest: UserDtoRequest): String {
        if (userDtoRequest.id == "" || userDtoRequest.password == "") {
            throw Exception("아이디 혹은 비밀번호를 입력하세요.")
        }

        val user: User? = userRepository.findByUserId(userDtoRequest.id)
        if (user == null) {
            throw Exception("존재하지 않는 ID입니다.")
        }
        if (userDtoRequest.password != user.userPassword) {
            throw Exception("ID 혹은 패스워드를 다시 확인하세요.")
        }

        user.lastLogin = Instant.now()
        userRepository.save(user)
        return "로그인 성공!"
    }
}