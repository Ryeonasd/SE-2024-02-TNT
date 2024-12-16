package TNT.SE_2024_02_TNT.dto


data class OrderDtoRequest( //주문 생성 시 리퀘스트로 들어오는 데이터
    val relay: String?,
    val order_id: String?,
    val container_id: String?,
    val pickup_time: String?,
    val estimated_arrival_time: String?,
    val order_info: OrderInfo
)

data class OrderInfo(
    val tracking_number: String?,
    val origin_address: Address,
    val destination_address: Address,
    val sender: ContactInfo,
    val receiver: ContactInfo
)

data class Address(
    val country: String?,
    val address: String?,
    val postalcode: Int?
)

data class ContactInfo(
    val name: String?,
    val email: String?,
    val phone: String?,
    val customs_id: String?
)
data class OrderCreateResponseDto(
    val message: String
)