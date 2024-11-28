package TNT.entity
import jakarta.persistence.*

// 보험 정보 엔티티
@Entity
@Table(name = "insurance_info")
data class InsuranceInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val company: String, // 보험사 이름

    @Column(nullable = false)
    val policyNumber: String, // 보험 번호

    @Column(nullable = false)
    val coverageAmount: Int, // 보장 금액

    @Column(nullable = false)
    val premium: Int // 보험료
) {
    constructor() : this(0, "", "", 0, 0) {

    }
}
