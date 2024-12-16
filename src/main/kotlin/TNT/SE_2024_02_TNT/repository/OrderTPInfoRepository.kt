package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.OrderTPInfo
import org.springframework.data.jpa.repository.JpaRepository

interface OrderTPInfoRepository:JpaRepository<OrderTPInfo, String> {
    fun findByTrnumOrder(trnumOrder: String): OrderTPInfo
}