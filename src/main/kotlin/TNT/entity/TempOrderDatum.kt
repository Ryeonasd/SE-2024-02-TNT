package TNT.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "TempOrderData")
open class TempOrderDatum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Int? = null

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "data", nullable = false)
    open var data: Map<String, Any>? = null
}