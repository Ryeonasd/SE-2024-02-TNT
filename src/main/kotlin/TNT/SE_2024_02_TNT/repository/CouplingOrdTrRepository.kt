package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.CouplingOrdTr
import TNT.SE_2024_02_TNT.entity.Order
import TNT.SE_2024_02_TNT.entity.OrderTPInfo
import org.springframework.data.jpa.repository.JpaRepository

interface CouplingOrdTrRepository: JpaRepository<CouplingOrdTr, Int> {
    fun findByIdOdr(order:Order): CouplingOrdTr?
    fun findByIdTr(trnumOrder: OrderTPInfo): CouplingOrdTr?
    fun deleteByIdOdr(order: Order)
    fun deleteByIdTr(trnumOrder: OrderTPInfo)
}