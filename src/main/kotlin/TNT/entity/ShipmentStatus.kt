package TNT.entity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "shipment_status")
data class ShipmentStatus(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "transport_info_id", nullable = false)
    val transportInfo: TransportInfo, // 운송 정보

    @Column(nullable = false)
    val currentStatus: String, // 현재 상태

    @Column(nullable = false)
    val lastUpdated: LocalDateTime // 상태 업데이트 시간
) {
    constructor() : this(0, TransportInfo(), "", LocalDateTime.now()) {

    }
}

