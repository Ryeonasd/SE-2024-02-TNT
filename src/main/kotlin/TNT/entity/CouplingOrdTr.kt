package TNT.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "coupling_ORD_TR")
open class CouplingOrdTr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ORDTR", nullable = false)
    open var id: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "id_ODR")
    open var idOdr: Order? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_TR")
    open var idTr: OrderTPInfo? = null
}