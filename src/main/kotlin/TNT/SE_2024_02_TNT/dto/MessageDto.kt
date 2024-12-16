package TNT.SE_2024_02_TNT.dto
import TNT.SE_2024_02_TNT.dto.ItemDto

data class MessageDto(
    val relay: Relay?,
    val order_info: OrderInfoDto?
)

data class OrderInfoDto(
    val shipping_info: ShippingInfoDto?,
    val items: List<ItemDto>?,
    val insurance_info: List<InsuranceInfoDto>?,
    val customs_info: List<CustomsInfoDto>?,
    val shipment_status: List<ShipmentStatusDto>?,
    val bill_of_lading: BillOfLadingDto?,
    val air_waybill: AirWaybillDto?
)

data class ShippingInfoDto(
    val tracking_number: String?,
    val origin_address: AddressDto?,
    val destination_address: AddressDto?,
    val sender: UserDto?,
    val receiver: UserDto?
)

data class AddressDto(
    val country: String?,
    val city: String?,
    val detail: String?,
    val postal_code: String?
)

data class UserDto(
    val name: String?,
    val email: String?,
    val phone: String?,
    val customs_id: String?
)

//data class ItemDto(
//    val tracking_number: String?,
//    val name: String?,
//    val is_imported: Boolean?,
//    val hs_code: String?,
//    val weight: Double?,
//    val quantity: Int?,
//    val x: Int?,
//    val y: Int?,
//    val z: Int?,
//    val hazardous: Boolean?,
//    val price: Int?
//)

data class InsuranceInfoDto(
    val company: String?,
    val policy_number: String?,
    val coverage_amount: Int?,
    val coverage_scope: String?,
    val start_date: String?,
    val end_date: String?,
    val premium: Int?
)

data class CustomsInfoDto(
    val duty_amount: Int?,
    val fta_applicable: Boolean?,
    val payment_method: String?,
    //val customs_declaration_number: String?
)

data class ShipmentStatusDto(
    val tracking_number: String?,
    val transport_vehicle_number: String?,
    val current_location: String?,
    val current_status: String?,
    val last_updated: String?,
    val remarks: String?
)

data class BillOfLadingDto(
    val shipper: UserInfoDto?,
    val consignee: UserInfoDto?,
    val bl_number: String?,
    val loading_port: String?,
    val discharge_port: String?,
    val vessel_name: String?,
    val vessel_number: String?,
    val shipment_date: String?
)

data class AirWaybillDto(
    val shipper: UserInfoDto?,
    val consignee: UserInfoDto?,
    val awb_number: String?,
    val departure_airport: String?,
    val destination_airport: String?,
    val aircraft_name: String?,
    val aircraft_number: String?,
    val shipment_date: String?
)

data class UserInfoDto(
    val name: String?,
    val address: String?,
    val contact: String?
)

enum class Relay {
    AIR, OCEAN, LAND, PARCEL
}
