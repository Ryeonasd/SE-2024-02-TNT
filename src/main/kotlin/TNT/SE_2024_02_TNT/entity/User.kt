package TNT.SE_2024_02_TNT.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant

@Entity
open class User {
    @Id
    @Column(name = "user_id", nullable = false, length = 50)
    open var userId: String? = null

    @Column(name = "user_password", nullable = false, length = 100)
    open var userPassword: String? = null

    @Column(name = "last_login")
    open var lastLogin: Instant? = null
}