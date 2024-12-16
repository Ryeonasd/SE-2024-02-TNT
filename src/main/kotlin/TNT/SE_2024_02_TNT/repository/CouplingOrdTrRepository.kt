package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.CouplingOrdTr
import TNT.SE_2024_02_TNT.entity.Order
import TNT.SE_2024_02_TNT.entity.OrderTPInfo
import org.springframework.data.jpa.repository.JpaRepository

interface CouplingOrdTrRepository: JpaRepository<CouplingOrdTr, Int> {
    fun findByOrder(order:Order): CouplingOrdTr?
    fun findByTrnumOrder(trnumOrder: OrderTPInfo): CouplingOrdTr?
    fun deleteByOrder(order: Order)
    fun deleteByTrnumOrder(trnumOrder: OrderTPInfo)
}