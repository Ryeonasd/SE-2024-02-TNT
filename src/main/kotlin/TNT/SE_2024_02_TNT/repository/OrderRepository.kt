package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.dto.OrderDtoRequest
import TNT.SE_2024_02_TNT.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long> {
    fun findAll(): List<Order>
}