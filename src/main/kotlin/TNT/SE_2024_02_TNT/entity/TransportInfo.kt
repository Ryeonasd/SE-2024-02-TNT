package TNT.SE_2024_02_TNT.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.Instant

@Entity
open class TransportInfo {
    @Id
    @Column(name = "tracking_number", nullable = false, length = 50)
    open var trackingNumber: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "container_id")
    open var container: Container? = null

    @Column(name = "origin_address", length = 20)
    open var originAddress: String? = null

    @Column(name = "destination_address", length = 20)
    open var destinationAddress: String? = null

    @Column(name = "pickup_time")
    open var pickupTime: Instant? = null

    @Column(name = "estimated_arrival_time")
    open var estimatedArrivalTime: Instant? = null

    @Lob
    @Column(name = "type")
    open var type: String? = null
}