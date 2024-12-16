package TNT.SE_2024_02_TNT.entity

import jakarta.persistence.*

@Entity
@Table(name = "Contacts")
open class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customs_id", nullable = false)
    open var id: Int? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_order", nullable = false)
    open var idOrder: Order? = null

    @Column(name = "name_depart", nullable = false, length = 100)
    open var nameDepart: String? = null

    @Column(name = "name_dest", nullable = false, length = 100)
    open var nameDest: String? = null

    @Column(name = "email_depart", length = 100)
    open var emailDepart: String? = null

    @Column(name = "email_dest", length = 100)
    open var emailDest: String? = null

    @Column(name = "phone_depart", length = 20)
    open var phoneDepart: String? = null

    @Column(name = "phone_dest", length = 20)
    open var phoneDest: String? = null

    @Column(name = "pccc_depart")
    open var pcccDepart: String? = null

    @Column(name = "pccc_dest")
    open var pcccDest: String? = null
}