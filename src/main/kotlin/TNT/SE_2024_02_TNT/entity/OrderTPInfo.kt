package TNT.SE_2024_02_TNT.entity

import jakarta.persistence.*

@Entity
open class OrderTPInfo {
    @Id
    @Column(name = "trnum_order", nullable = false, length = 50)
    open var trnumOrder: String? = null

    @Column(name = "postalcode_dest", nullable = false)
    open var postalcodeDest: Int? = null

    @Column(name = "postalcode_depart", nullable = false)
    open var postalcodeDepart: Int? = null

    @Column(name = "nation_dest", nullable = false, length = 50)
    open var nationDest: String? = null

    @Column(name = "nation_depart", nullable = false, length = 50)
    open var nationDepart: String? = null

    @Lob
    @Column(name = "address_dest", nullable = false)
    open var addressDest: String? = null

    @Lob
    @Column(name = "address_depart", nullable = false)
    open var addressDepart: String? = null

    @OneToMany(mappedBy = "idTr")
    open var couplingOrdTrs: MutableSet<CouplingOrdTr> = mutableSetOf()
}