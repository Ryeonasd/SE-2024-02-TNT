package TNT.SE_2024_02_TNT.dto

import java.time.Instant
import java.time.LocalDateTime

data class OrderDtoSearch ( // 주문 목록을 받을 때 리스트에 들어가는 정보
    val orderId : String?,
    val nameDepart: String?,
    val originAddress: String?,
    val destinationAddress: String?,
    val currentStatus: String?,
    val containerId: String?,
    val pickupTime: Instant?,
    val estimatedArrivalTime: Instant?,
)