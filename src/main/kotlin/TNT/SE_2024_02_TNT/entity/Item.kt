package TNT.SE_2024_02_TNT.entity

import jakarta.persistence.*

@Entity
@Table(name = "Items")
open class Item {
    @Id
    @Column(name = "tracking_number", nullable = false, length = 50)
    open var trackingNumber: String? = null

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tracking_number", nullable = false)
    open var transportInfo: TransportInfo? = null

    @Column(name = "name", nullable = false, length = 100)
    open var name: String? = null

    @Column(name = "hs_code", length = 50)
    open var hsCode: String? = null

    @Column(name = "is_imported")
    open var isImported: Boolean? = null

    @Column(name = "weight")
    open var weight: Double? = null

    @Column(name = "dimensions", length = 50)
    open var dimensions: String? = null

    @Column(name = "hazardous")
    open var hazardous: Boolean? = null

    @Column(name = "price")
    open var price: Double? = null

    @Column(name = "quantity")
    open var quantity: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "container_id")
    open var container: Container? = null
}