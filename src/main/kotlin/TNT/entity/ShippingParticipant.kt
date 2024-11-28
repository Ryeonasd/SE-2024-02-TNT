package TNT.entity

import jakarta.persistence.*

@Entity
@Table(name = "shipping_participants")
data class ShippingParticipant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String, // 이름

    @Column(nullable = false)
    val email: String, // 이메일

    @Column(nullable = false)
    val phone: String, // 전화번호
) {
    constructor() : this(0, "", "", "") {

    }
}
