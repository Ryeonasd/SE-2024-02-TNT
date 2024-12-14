package TNT.SE_2024_02_TNT.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "Containers")
open class Container {
    @Id
    @Column(name = "container_id", nullable = false, length = 50)
    open var containerId: String? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id", nullable = false)
    open var order: Order? = null

    @OneToMany(mappedBy = "container")
    open var items: MutableSet<Item> = mutableSetOf()

    @OneToMany(mappedBy = "container")
    open var transportInfos: MutableSet<TransportInfo> = mutableSetOf()
}