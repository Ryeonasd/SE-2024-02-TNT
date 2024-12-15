package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, String> {
    fun findByOrderId(orderId: String): Order?
}