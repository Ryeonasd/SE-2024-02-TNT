package TNT.SE_2024_02_TNT.dto

data class OrderDtoSearch ( // 주문 목록을 받을 때 리스트에 들어가는 정보
    val orderId : String,
    val nameDepart: String,
    val originAddress: String,
    val destinationAddress: String,
    val containerId: String,
    val pickupDime: LocalDateTime,
    val estimatedArrivalTime: LocalDateTime
)