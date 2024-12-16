package TNT.SE_2024_02_TNT.dto

import java.time.Instant
// 운송 현황 get 요청시에 사용되는 DTO
data class ShipmentStatusDtoSearch(
    val status_id : String?,
    val order_id: String?,
    val current_location: String?,
    val current_status : String?,
    val last_updated: Instant?,
    val remarks : String?,
    val transport_vehicle_num:String?
)