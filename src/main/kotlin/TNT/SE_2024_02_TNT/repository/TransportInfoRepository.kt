package TNT.SE_2024_02_TNT.repository


import TNT.SE_2024_02_TNT.entity.Container
import org.springframework.data.jpa.repository.JpaRepository
import TNT.SE_2024_02_TNT.entity.TransportInfo

interface TransportInfoRepository : JpaRepository<TransportInfo, String> {
    fun findByTrackingNumber(trackingNumber: String): TransportInfo?
    fun findAllByContainer(container: Container): MutableList<TransportInfo>?
    fun findByContainer(container: Container): TransportInfo?
    fun deleteByTrackingNumber(trackingNumber: String)
}