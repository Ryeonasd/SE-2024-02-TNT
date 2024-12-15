package TNT.SE_2024_02_TNT.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.JpaRepository
import TNT.SE_2024_02_TNT.dto.OrderProjection
import TNT.SE_2024_02_TNT.entity.Order

interface OrderListRepository : JpaRepository<Order, Long> {

    @Query("""
        SELECT new TNT.SE_2024_02_TNT.dto.OrderProjection(
            o.orderId, c.nameDepart, o.trackingNumber, ot.addressDepart, ot.addressDest, 
            s.currentStatus, i.name, ct.containerId, ti.estimatedArrivalTime, i.price
        )
        FROM Order o
        JOIN o.contacts c
        JOIN o.containers ct
        JOIN ct.transportInfos ti
        JOIN ti.item i
        JOIN o.shipmentStatuses s
        JOIN o.couplingOrdTrs cot
        JOIN cot.idTr ot
    """)
    fun findAllOrders(): List<OrderProjection>
}
