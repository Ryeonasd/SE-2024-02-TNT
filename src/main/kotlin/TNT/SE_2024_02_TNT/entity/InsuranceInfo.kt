package TNT.SE_2024_02_TNT.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate

@Entity
open class InsuranceInfo {
    @Id
    @Column(name = "insurance_id", nullable = false, length = 50)
    open var insuranceId: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id")
    open var order: Order? = null

    @Column(name = "company", length = 100)
    open var company: String? = null

    @Column(name = "policy_number", length = 50)
    open var policyNumber: String? = null

    @Column(name = "coverage_amount")
    open var coverageAmount: Double? = null

    @Column(name = "coverage_scope")
    open var coverageScope: String? = null

    @Column(name = "start_date")
    open var startDate: LocalDate? = null

    @Column(name = "end_date")
    open var endDate: LocalDate? = null

    @Column(name = "premium")
    open var premium: Double? = null
}