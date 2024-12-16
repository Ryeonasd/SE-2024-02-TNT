package TNT.SE_2024_02_TNT.repository

import TNT.SE_2024_02_TNT.entity.Container
import TNT.SE_2024_02_TNT.entity.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<Item, String> {
    fun findByTrackingNumber(trackingNumber: String) : Item?
    fun deleteByTrackingNumber(trackingNumber: String)
    abstract fun findByContainer(container: Container): MutableList<Item>
}
