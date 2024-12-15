package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.Contact
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository: JpaRepository<Contact, Int> {
}