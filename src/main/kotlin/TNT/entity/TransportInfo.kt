package TNT.entity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "transport_info")
data class TransportInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val relay: RelayType, // 배송 경로

    @Column(nullable = false)
    val pickupTime: LocalDateTime, // 픽업 시간

    @Column(nullable = false)
    val estimatedArrivalTime: LocalDateTime, // 예상 도착 시간

    @Column(nullable = false)
    val currentLocation: String // 현재 위치
) {
    constructor() : this(0, RelayType.NONE, LocalDateTime.now(), LocalDateTime.now(), "") {

    }
}

enum class RelayType {
    OCEAN, AIRLINE, PARCEL, LAND, NONE
}
