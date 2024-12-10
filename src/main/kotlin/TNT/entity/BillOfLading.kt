package TNT.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate

@Entity
open class BillOfLading {
    @Id
    @Column(name = "bl_number", nullable = false, length = 50)
    open var blNumber: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    open var order: Order? = null

    @Column(name = "shipper_name", length = 100)
    open var shipperName: String? = null

    @Column(name = "shipper_address")
    open var shipperAddress: String? = null

    @Column(name = "consignee_name", length = 100)
    open var consigneeName: String? = null

    @Column(name = "consignee_address")
    open var consigneeAddress: String? = null

    @Column(name = "loading_port", length = 100)
    open var loadingPort: String? = null

    @Column(name = "discharge_port", length = 100)
    open var dischargePort: String? = null

    @Column(name = "vessel_name", length = 100)
    open var vesselName: String? = null

    @Column(name = "shipment_date")
    open var shipmentDate: LocalDate? = null
}