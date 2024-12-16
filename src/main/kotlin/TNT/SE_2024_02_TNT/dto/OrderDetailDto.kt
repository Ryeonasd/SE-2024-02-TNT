package TNT.SE_2024_02_TNT.dto

import java.time.Instant

data class OrderDetailDto(
    val order_id: String,
    val name_depart: String,
    val user_id: String,
    val weight: Double,
    val hs_code: String,
    val item_name: String,
    val price: Int,
    val origin_address: String,
    val destination_address: String,
    val tracking_number: String,
    val estimated_arrival_time: Instant,
    val delivery_status: List<DeliveryStatusDto>
)

data class DeliveryStatusDto(
    val status_id: String,
    val remarks: String,
    val last_updated: Instant
)
