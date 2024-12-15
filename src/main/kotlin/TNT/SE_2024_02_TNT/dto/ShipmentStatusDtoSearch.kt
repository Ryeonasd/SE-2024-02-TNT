package TNT.SE_2024_02_TNT.dto

import TNT.SE_2024_02_TNT.entity.Order
import java.time.Instant
// 운송 현황 get 요청시에 사용되는 DTO
data class ShipmentStatusDtoSearch(
    val statusId : String?,
    val order: Order,
    val currentLocation: String?,
    val currentStatus : String?,
    val lastUpdated: Instant?,
    val remarks : String?,
    val transportVehicleNum:String?
)