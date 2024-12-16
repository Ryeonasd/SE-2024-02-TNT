package TNT.SE_2024_02_TNT.service

import TNT.SE_2024_02_TNT.dto.*
import TNT.SE_2024_02_TNT.entity.*
import TNT.SE_2024_02_TNT.repository.*
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Transactional
@Service
class OrderService(
    val orderRepository: OrderRepository,
    val contactRepository: ContactRepository,
    val couplingOrdTrRepository: CouplingOrdTrRepository,
    val containerRepository: ContainerRepository,
    val shipmentStatusRepository: ShipmentStatusRepository,
    val orderTPInfoRepository: OrderTPInfoRepository,
    val transportInfoRepository:TransportInfoRepository,
    val itemRepository: ItemRepository,
    val insurenceRepository: InsurenceRepository,
    val customsInfoRepository: CustomInfoRepository,

) {
    fun getAllOrderList(): List<OrderDtoSearch> {
        return orderRepository.findAll().map { order ->
            OrderDtoSearch(
                order.orderId,
                order.contacts.firstOrNull()?.nameDepart,
                order.couplingOrdTrs.firstOrNull()?.idTr?.addressDepart,
                order.couplingOrdTrs.firstOrNull()?.idTr?.addressDest,
                order.shipmentStatuses.firstOrNull()?.currentStatus,
                order.containers.firstOrNull()?.containerId,
                order.couplingOrdTrs.firstOrNull()?.idTr?.pickupTime,
                order.couplingOrdTrs.firstOrNull()?.idTr?.estimatedArrivalTime
            )
        }
    }

    @Transactional
    fun createOrder(request: OrderDtoRequest): OrderCreateResponseDto {
        val orderTPInfo = OrderTPInfo().apply {
            trnumOrder = request.order_info.tracking_number
            postalcodeDepart = request.order_info.origin_address.postalcode
            postalcodeDest = request.order_info.destination_address.postalcode
            nationDepart = request.order_info.origin_address.country
            nationDest = request.order_info.destination_address.country
            addressDepart = request.order_info.origin_address.address
            addressDest = request.order_info.destination_address.address
            pickupTime = Instant.parse(request.pickup_time)
            estimatedArrivalTime = Instant.parse(request.estimated_arrival_time)
        }
        orderTPInfoRepository.save(orderTPInfo)

        // Order 엔티티 생성 및 저장
        val order = Order(
            orderId = request.order_id,
            trackingNumber = request.order_info.tracking_number,
            containerNumber = request.container_id,
            relay = request.relay
        )
        orderRepository.save(order)
        // 발송인, 수취인 정보 저장
        val contact = Contact().apply {
            idOrder = order
            nameDepart = request.order_info.sender.name
            emailDepart = request.order_info.sender.email
            phoneDepart = request.order_info.sender.phone
            pcccDepart = request.order_info.sender.customs_id
            nameDest = request.order_info.receiver.name
            emailDest = request.order_info.receiver.email
            phoneDest = request.order_info.receiver.phone
            pcccDest = request.order_info.receiver.customs_id
        }
        contactRepository.save(contact)
        // OrderTPInfo 엔티티 생성 및 저장

        // order, ordertpinfo 연결
        val couplingOrdTr = CouplingOrdTr().apply {
            this.idOdr = order
            this.idTr = orderTPInfo
        }
        couplingOrdTrRepository.save(couplingOrdTr)

        return OrderCreateResponseDto(message = "주문 생성 완료")
    }

    // order_id로 주문 검색
    fun searchOrderByOrderId(orderId: String): OrderDtoSearch? {
        val order = orderRepository.findByOrderId(orderId)
        if (order == null) throw Exception("찾는 주문 정보가 없습니다.")
        return OrderDtoSearch(
            order.orderId,
            order.contacts.firstOrNull()?.nameDepart,
            order.couplingOrdTrs.firstOrNull()?.idTr?.addressDepart,
            order.couplingOrdTrs.firstOrNull()?.idTr?.addressDest,
            order.shipmentStatuses.firstOrNull()?.currentStatus,
            order.containers.firstOrNull()?.containerId,
            order.couplingOrdTrs.firstOrNull()?.idTr?.pickupTime,
            order.couplingOrdTrs.firstOrNull()?.idTr?.estimatedArrivalTime
        )
    }

    // container_id로 주문 검색
    fun searchOrderByContainerId(containerId: String): OrderDtoSearch? {
        val container = containerRepository.findByContainerId(containerId) ?: throw Exception("찾는 컨테이너 정보가 없습니다.")
        val order = container.order!!
        return OrderDtoSearch(
            order.orderId,
            order.contacts.firstOrNull()?.nameDepart,
            order.couplingOrdTrs.firstOrNull()?.idTr?.addressDepart,
            order.couplingOrdTrs.firstOrNull()?.idTr?.addressDest,
            order.shipmentStatuses.firstOrNull()?.currentStatus,
            order.containers.firstOrNull()?.containerId,
            order.couplingOrdTrs.firstOrNull()?.idTr?.pickupTime,
            order.couplingOrdTrs.firstOrNull()?.idTr?.estimatedArrivalTime
        )
    }

    // 운송 현황 상태 DB에서 가져오기
    fun getShipmentStatusList(orderId: String): List<ShipmentStatusDtoSearch> {
        val order = orderRepository.findByOrderId(orderId)
        val shipmentStatuses = order?.shipmentStatuses ?: throw Exception()
        return shipmentStatuses.map { shipmentStatus ->
            ShipmentStatusDtoSearch(
                status_id = shipmentStatus.statusId?.toString(),
                order_id = order.orderId, // 연관된 Order 객체
                current_location = shipmentStatus.currentLocation,
                current_status = shipmentStatus.currentStatus,
                last_updated = shipmentStatus.lastUpdated ?: Instant.now(), // null 처리
                remarks = shipmentStatus.remarks,
                transport_vehicle_num = shipmentStatus.transportVehicleNum
            )
        }
    }

    // 변경한 운송현황 값 DB에 갖다넣기
    @Transactional
    fun updateShipmentStatus(dto: ShipmentStatusDtoSearch): Boolean {
        val order = orderRepository.findByOrderId(dto.order_id!!) ?: throw IllegalArgumentException("해당 주문 ID가 없습니다.")
        val allStatuses = order.shipmentStatuses
        val recentStatus = allStatuses.maxByOrNull { it.statusId?.toIntOrNull() ?: 0 }
//        // statusId가 가장 큰 값에서 1을 더해 새 Id를 생성
//        val newStatusId = recentStatus?.statusId?.toIntOrNull()?.plus(1) ?: 1

        val newStatus = ShipmentStatus().apply {
            statusId = dto.status_id
            this.order = order
            currentLocation = dto.current_location ?: recentStatus?.currentLocation // null이면 기존 값 유지
            currentStatus = dto.current_status ?: recentStatus?.currentStatus // null이면 기존 값 유지
            lastUpdated = Instant.now() // 자동 업데이트
            remarks = dto.remarks
            transportVehicleNum = dto.transport_vehicle_num ?: recentStatus?.transportVehicleNum
        }

        shipmentStatusRepository.save(newStatus)
        return true
    }

/*    fun parseOrderRequest(orderRequest: Map<String, Any?>): Order {
        val order = Order()
        //todo : shipping info extraction
        //todo : Order Table data extraction
        //todo : return data
        return order
    }*/

    fun getOrderDetails(orderId: String): OrderDetailDto? {
        val order = orderRepository.findById(orderId).orElse(null)
        val container = order.containerNumber?.let { containerRepository.findByContainerId(it) }
        val items = container?.let { itemRepository.findByContainer(it) }
        val tpinfos = container?.let { transportInfoRepository.findAllByContainer(it) }
        val ship = order?.shipmentStatuses ?: throw Exception()
        val contact = contactRepository.findByIdOrder(order)
        val insurances = insurenceRepository.findByOrder(order)
        val customs = customsInfoRepository.findByOrder(order)

        //order로 CouplingOrdTr 찾기
        val couplingOrderTransportInfo = couplingOrdTrRepository.findByOrder(order)
        //order 에서 OrderTPInfo 찾기
        val OrderTR = couplingOrderTransportInfo?.idTr

        return OrderDetailDto(
            order_id = order.orderId ?: "",
            name_depart = contact?.nameDepart ?:      "",
            items = items!!,
            transportInfos = tpinfos!!,
            delivery_status = ship,
            origin_address = OrderTR?.addressDepart ?: "",
            destination_address = OrderTR?.addressDest!!,
            tracking_number = order.trackingNumber !!,
            estimated_arrival_time = OrderTR.estimatedArrivalTime,
            insurances = insurances,
            customs = customs,
        )
   }


}