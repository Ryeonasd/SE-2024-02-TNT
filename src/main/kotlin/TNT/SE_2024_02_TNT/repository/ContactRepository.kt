package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.Contact
import TNT.SE_2024_02_TNT.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository: JpaRepository<Contact, Int> {
    fun findByIdOrder(idOrder: Order): Contact
}