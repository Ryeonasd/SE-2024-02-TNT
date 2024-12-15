package TNT.SE_2024_02_TNT.dto

// Get 요청시에 사용되는 DTO
data class ShipmentStatusDto {
    val statusID : string;
    val currentLocation: string;
    val currentStatus : string;
    val lastUpdated : string;
    val remarks : string;
    val transportVehicleNumber :string;
    
}