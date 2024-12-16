package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.Order
import org.springframework.data.jpa.repository.JpaRepository
import TNT.SE_2024_02_TNT.entity.ShipmentStatus


interface ShipmentStatusRepository : JpaRepository<ShipmentStatus, String> {
    //fun findByOrderId(orderId: String): MutableList<ShipmentStatus>
    fun findAllByOrder(order: Order): MutableList<ShipmentStatus>
}

