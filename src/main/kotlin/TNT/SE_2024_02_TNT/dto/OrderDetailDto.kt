package TNT.SE_2024_02_TNT.dto

import java.time.Instant

data class OrderDetailDto(
    val orderId: String,
    val nameDepart: String,
    val userId: String,
    val weight: Double,
    val hsCode: String,
    val itemName: String,
    val price: Int,
    val originAddress: String,
    val destinationAddress: String,
    val trackingNumber: String,
    val estimatedArrivalTime: Instant,
    val deliveryStatus: List<DeliveryStatusDto>
)

data class DeliveryStatusDto(
    val statusId: String,
    val remarks: String,
    val lastUpdated: Instant
)
