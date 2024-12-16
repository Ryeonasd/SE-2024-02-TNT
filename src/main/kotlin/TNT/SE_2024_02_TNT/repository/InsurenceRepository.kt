package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.InsuranceInfo
import TNT.SE_2024_02_TNT.entity.Order
import org.springframework.data.jpa.repository.JpaRepository


interface InsurenceRepository:JpaRepository<InsuranceInfo,String > {
    fun findByInsuranceId(insuranceId: String): MutableList<InsuranceInfo>
    fun findByOrder(order: Order): MutableList<InsuranceInfo>
}