package TNT.entity
import jakarta.persistence.*
@Entity
@Table(name = "customs_info")
data class CustomsInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val dutyAmount: Int, // 관세 금액

    @Column(nullable = false)
    val ftaApplicable: Boolean, // FTA 혜택 여부

    @Column(nullable = false)
    val customsDeclarationNumber: String // 통관 신고 번호
) {
    constructor() : this(0, 0, false, "") {

    }
}
