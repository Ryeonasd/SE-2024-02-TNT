package TNT.SE_2024_02_TNT.dto

import java.time.Instant


data class OrderProjection(
    val orderId: String,
    val nameDepart: String,
    val userId: String,
    val originAddress: String,
    val destinationAddress: String,
    val currentStatus: String,
    val name: String,
    val containerId: String,
    val estimatedArrivalTime: Instant?,
    val price: Double?
)