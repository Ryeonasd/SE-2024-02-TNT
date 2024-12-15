package TNT.SE_2024_02_TNT.dto

data class ItemAssignDtoRequest (
    val containerId: String,
    val data: List<ItemData>,
)  // 컨테이너에 물품을 넣을때 사용하는 데이터

data class ItemData (
    val trackingNumber: String?,
    val name: String?,
    val isImported: Boolean?,
    val hsCode: String?,
    val weight: Double?,
    val quantity: Int?,
    val dimensions: String?,
    val hazardous: Boolean?,
    val price: Double?,
    val originAddress: String?,
    val destinationAddress: String?,
    val pickupTime: String?,
    val estimatedArrivalTime: String?
)

// db에 넣을 때 둘로 쪼개 넣음
data class Item (
    val trackingNumber: String?,
    val name: String?,
    val isImported: Boolean?,
    val hsCode: String?,
    val weight: Double?,
    val quantity: Int?,
    val dimensions: String?,
    val hazardous: Boolean?,
    val price: Double?
)

data class ItemTransportInfo (    
    val trackingNumber: String?,
    val originAddress: String?,
    val destinationAddress: String?,
    val pickupTime: String?,
    val estimatedArrivalTime: String?
)