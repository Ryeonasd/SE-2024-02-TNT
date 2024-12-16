package TNT.SE_2024_02_TNT.dto

import TNT.SE_2024_02_TNT.entity.Item
import TNT.SE_2024_02_TNT.entity.TransportInfo
import TNT.SE_2024_02_TNT.entity.ShipmentStatus
import TNT.SE_2024_02_TNT.entity.InsuranceInfo
import TNT.SE_2024_02_TNT.entity.CustomsInfo
import java.time.Instant

data class OrderDetailDto(
    val order_id: String,
    val name_depart: String,
    val items: List<Item>,
    val transportInfos: List<TransportInfo>,
    val origin_address: String,
    val destination_address: String,
    val tracking_number: String,
    val estimated_arrival_time: Instant?,
    val delivery_status: Set<ShipmentStatus>,
    val insurances:List<InsuranceInfo>?,
    val customs: List<CustomsInfo>?,
)

