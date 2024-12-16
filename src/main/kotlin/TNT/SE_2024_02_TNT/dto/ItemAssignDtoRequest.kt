package TNT.SE_2024_02_TNT.dto

import java.time.Instant

data class ItemAssignDtoRequest(
    val container_id: String,
    val data: List<ItemDataDto>
)

data class ItemDataDto(
    val tracking_number: String?,
    val name: String?,
    val is_imported: Boolean?,
    val hs_code: String?,
    val weight: Double?,
    val quantity: Int?,
    val x: Int?,  // 가로
    val y: Int?,  // 세로
    val z: Int?,  // 높이
    val hazardous: Boolean?,
    val price: Double?,
    val origin_address: String?,
    val destination_address: String?,
    val pickup_time: Instant?,
    val estimated_arrival_time: Instant?
)

// DB에 삽입할 DTO 분리
data class ItemDto(
    val tracking_number: String?,
    val name: String?,
    val is_imported: Boolean?,
    val hs_code: String?,
    val weight: Double?,
    val quantity: Int?,
    val dimensions: String?,  // "x*y*z" 형태로 저장
    val hazardous: Boolean?,
    val price: Double?
)

data class ItemTransportInfo(
    val tracking_number: String?,
    val origin_address: String?,
    val destination_address: String?,
    val pickup_time: Instant?,
    val estimated_arrival_time: Instant?
)
