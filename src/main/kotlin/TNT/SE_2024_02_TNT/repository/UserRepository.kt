package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUserId(username: String): User?
}