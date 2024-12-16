package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.CustomsInfo
import TNT.SE_2024_02_TNT.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface CustomInfoRepository : JpaRepository<CustomsInfo, String> {
    fun findByCustomsId(customsId: String): CustomsInfo?
    fun findByOrderNumber(order: Order): MutableList<CustomsInfo>?
}