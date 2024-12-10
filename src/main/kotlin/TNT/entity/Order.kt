package TNT.entity

import jakarta.persistence.*

@Entity
@Table(name = "Orders")
open class Order {
    @Id
    @Column(name = "order_id", nullable = false, length = 50)
    open var orderId: String? = null

    @Column(name = "tracking_number", nullable = false, length = 50)
    open var trackingNumber: String? = null

    @Column(name = "container_number", length = 50)
    open var containerNumber: String? = null

    @Lob
    @Column(name = "relay")
    open var relay: String? = null

    @OneToMany(mappedBy = "order")
    open var airWaybills: MutableSet<AirWaybill> = mutableSetOf()

    @OneToMany(mappedBy = "order")
    open var billOfLadings: MutableSet<BillOfLading> = mutableSetOf()

    @OneToMany(mappedBy = "idOrder")
    open var contacts: MutableSet<Contact> = mutableSetOf()

    @OneToMany(mappedBy = "order")
    open var containers: MutableSet<Container> = mutableSetOf()

    @OneToMany(mappedBy = "orderNumber")
    open var customsInfos: MutableSet<CustomsInfo> = mutableSetOf()

    @OneToMany(mappedBy = "order")
    open var insuranceInfos: MutableSet<InsuranceInfo> = mutableSetOf()

    @OneToMany(mappedBy = "order")
    open var shipmentStatuses: MutableSet<ShipmentStatus> = mutableSetOf()

    @OneToMany(mappedBy = "idOdr")
    open var couplingOrdTrs: MutableSet<CouplingOrdTr> = mutableSetOf()
}