package TNT.SE_2024_02_TNT.dto

import java.time.Instant

data class OrderDtoSearch ( // 주문 목록을 받을 때 리스트에 들어가는 정보
    val order_id : String?,
    val name_depart: String?,
    val origin_address: String?,
    val destination_address: String?,
    val current_status: String?,
    val container_id: String?,
    val pickup_time: Instant?,
    val estimated_arrival_time: Instant?,
)