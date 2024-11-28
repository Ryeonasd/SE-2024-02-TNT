package TNT.entity
import jakarta.persistence.*
@Entity
@Table(name = "sender_receiver_info")
data class SenderReceiverInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false, unique = true)
    val phone: String,

    @Column(name = "customs_id", nullable = false)
    val customsId: String
) {
    constructor() : this(0, "", "", "", "") {

    }
}

