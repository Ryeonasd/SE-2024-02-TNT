package TNT.entity
import jakarta.persistence.*
// Each item in the Pakage entity
@Entity
@Table(name = "items")
data class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String, // 물품명

    @Column(nullable = false)
    val hsCode: String, // HS 코드

    @Column(nullable = false)
    val weight: Double, // 무게

    @Column(nullable = false)
    val quantity: Int, // 수량

    @Column(nullable = false)
    val price: Int, // 가격

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order // 주문 정보와의 연관 관계
) {
    constructor() : this(0, "", "", 0.0, 0, 0, Order()) {

    }
}

