package TNT.entity
import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val orderNumber: String, // 주문 번호

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "shipping_info_id", referencedColumnName = "id")
    val shippingInfo: ShippingInfo, // 배송 정보

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    val sender: ShippingParticipant, // 발송인

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    val receiver: ShippingParticipant, // 수취인

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    val items: List<Item> = emptyList(), // 물품 정보 목록

    @Column(nullable = false)
    val trackingNumber: String, // 운송장 번호

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "insurance_info_id", referencedColumnName = "id")
    val insuranceInfo: InsuranceInfo?, // 보험 정보

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "customs_info_id", referencedColumnName = "id")
    val customsInfo: CustomsInfo? // 관세 정보
) {
    constructor() : this(0, "", ShippingInfo(), ShippingParticipant(), ShippingParticipant(), emptyList(), "", null, null) {

    }
}

