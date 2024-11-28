package TNT.entity
import jakarta.persistence.*
@Entity
@Table(name = "shipping_info")
data class ShippingInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Embedded
    val originAddress: Address, // 출발지 주소

    @Embedded
    @AttributeOverrides(
        AttributeOverride(name = "city", column = Column(name = "destination_city")),
        AttributeOverride(name = "detail", column = Column(name = "destination_detail")),
        AttributeOverride(name = "postalCode", column = Column(name = "destination_postal_code"))
    )
    val destinationAddress: Address, // 도착지 주소

    @Column(nullable = false)
    val containerNumber: String // 컨테이너 번호
) {
    constructor() : this(0, Address(), Address(), "") {

    }
}

@Embeddable
data class Address(
    @Column(nullable = false)
    val city: String, // 도시

    @Column(nullable = false)
    val detail: String, // 상세 주소

    @Column(nullable = false)
    val postalCode: String // 우편번호
) {
    constructor() : this("", "", "") {

    }
}

