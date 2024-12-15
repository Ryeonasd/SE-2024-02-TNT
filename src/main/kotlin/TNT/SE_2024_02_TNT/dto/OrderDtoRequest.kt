package TNT.SE_2024_02_TNT.dto

data class OrderDtoRequest( //주문 생성 시 리퀘스트로 들어오는 데이터
    val relay: String,
    val orderId: String,
    val containerId: String,
    val pickupTime: String,
    val estimatedArrivalTime: String,
    val trackingNumber: String,
    val originAddress: Address,
    val destinationAddress: Address,
    val sender: ContactInfo,
    val receiver: ContactInfo
)

data class Address(
    val country: String,
    val city: String,
    val detail: String,
    val postalCode: String
)

data class ContactInfo(
    val name: String,
    val email: String,
    val phone: String,
    val customsId: String
)

data class SearchOrdersRequest(val data: String)