package TNT.SE_2024_02_TNT.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
open class CustomsInfo {
    @Id
    @Column(name = "customs_id", nullable = false, length = 50)
    open var customsId: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_number")
    open var orderNumber: Order? = null

    @Column(name = "duty_amount")
    open var dutyAmount: Double? = null

    @Column(name = "fta_applicable")
    open var ftaApplicable: Boolean? = null

    @Column(name = "payment_method", length = 50)
    open var paymentMethod: String? = null

    @Column(name = "customs_declaration_number", length = 50)
    open var customsDeclarationNumber: String? = null
}