package TNT.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.Instant

@Entity
open class ShipmentStatus {
    @Id
    @Column(name = "status_id", nullable = false, length = 50)
    open var statusId: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    open var order: Order? = null

    @Column(name = "current_location", length = 100)
    open var currentLocation: String? = null

    @Lob
    @Column(name = "current_status")
    open var currentStatus: String? = null

    @Column(name = "last_updated")
    open var lastUpdated: Instant? = null

    @Lob
    @Column(name = "remarks")
    open var remarks: String? = null
}