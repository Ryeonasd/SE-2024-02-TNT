package TNT.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate

@Entity
open class AirWaybill {
    @Id
    @Column(name = "awb_number", nullable = false, length = 50)
    open var awbNumber: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    open var order: Order? = null

    @Column(name = "departure_airport", length = 100)
    open var departureAirport: String? = null

    @Column(name = "destination_airport", length = 100)
    open var destinationAirport: String? = null

    @Column(name = "aircraft_name", length = 100)
    open var aircraftName: String? = null

    @Column(name = "shipment_date")
    open var shipmentDate: LocalDate? = null
}