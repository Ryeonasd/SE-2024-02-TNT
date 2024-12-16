package TNT.SE_2024_02_TNT.service

import TNT.SE_2024_02_TNT.dto.*
import TNT.SE_2024_02_TNT.entity.*
import TNT.SE_2024_02_TNT.repository.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.Instant

@Transactional
@Service
class OrderService(
    val orderRepository: OrderRepository,
    val contactRepository: ContactRepository,
    val containerRepository: ContainerRepository,
    val shipmentStatusRepository: ShipmentStatusRepository,
    val orderTPInfoRepository: OrderTPInfoRepository,
    val transportInfoRepository:TransportInfoRepository,
    val itemRepository: ItemRepository
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
        // Order 엔티티 생성 및 저장
        val order = Order(
            orderId = request.order_id,
            trackingNumber = request.order_info.tracking_number,
            containerNumber = request.container_id,
            relay = request.relay
        )
        orderRepository.save(order)

        // OrderTPInfo 엔티티 생성 및 저장
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

        // Container 엔티티 생성 및 저장
        val container = Container().apply {
            containerId = request.container_id
            this.order = order
        }
        containerRepository.save(container)

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
    fun updateShipmentStatus(
        orderId: String,
        currentStatus: String?,
        remarks: String?,
        transportVehicleNum: String?
    ): Boolean {
        val shipmentStatus = shipmentStatusRepository.findByStatusId(orderId)
            ?: throw IllegalArgumentException("해당 주문 ID에 대한 운송 상태가 없습니다.")

        shipmentStatus.apply {
            this.currentStatus = currentStatus ?: this.currentStatus // null이면 기존 값 유지
            this.lastUpdated = Instant.now() // 자동 업데이트
            this.remarks = remarks ?: this.remarks
            this.transportVehicleNum = transportVehicleNum ?: this.transportVehicleNum
        }

        shipmentStatusRepository.save(shipmentStatus)
        return true
    }

    fun parseOrderRequest(orderRequest: Map<String, Any?>): Order {
        val order = Order()
        //todo : shipping info extraction
        //todo : Order Table data extraction
        //todo : return data
        return order
    }

//    fun getOrderDetails(orderId: String): OrderDetailDto? {
//        val order = orderRepository.findById(orderId).orElse(null)
//        val container = containerRepository.findByContainerId(order.containerNumber ?: throw IllegalArgumentException("Container number not found"))
//        val items = itemRepository.findByContainer(container) ?: throw IllegalArgumentException("Items not found")
//        val item = items.firstOrNull()
//        // ship = shipmentStatusRepository.findByOrderId(orderId)
//        val ship = order?.shipmentStatuses ?: throw Exception()
//        val tp = transportInfoRepository.findByContainer(order.containerNumber ?: throw IllegalArgumentException("Tracking number not found"))
//        // findByContainerId로 수정
//        val contact = ContactRepository.findByIdOrder(order).orElse(null)
//
//        return OrderDetailDto(
//            orderId = order.orderId ?: "",
//            nameDepart = contact?.nameDepart ?: "",
//            userId = contact.id ?: "",
//            weight = item?.weight ?: 0.0,
//            hsCode = item?.hsCode?: "",
//            itemName = item?.name ?: "",
//            price = item?.price?.toInt() ?: 0,
//            originAddress = tp?.originAddress ?: "",
//            destinationAddress = tp?.destinationAddress ?: "",
//            trackingNumber = order.trackingNumber ?: "",
//            estimatedArrivalTime = tp?.estimatedArrivalTime ?: Instant.now(),
//            deliveryStatus = ship.map { status ->
//                DeliveryStatusDto(
//                    statusId = status.statusId ?:"",
//                    remarks = status.remarks ?: "",
//                    lastUpdated = status.lastUpdated ?: Instant.now()
//                )
//            }
//        )

//        return order?.let {
//            OrderDetailDto(
//                orderId = it.orderId ?: "",
//                nameDepart = contact?.nameDepart ?: "",
//                userId = contact.id ?: "",
//                weight = item?.weight ?: 0.0,
//                hsCode = item?.hsCode?: "",
//                itemName = item?.name ?: "",
//                price = item?.price?.toInt() ?: 0,
//                originAddress = tp?.originAddress ?: "",
//                destinationAddress = tp?.destinationAddress ?: "",
//                trackingNumber = it.trackingNumber ?: "",
//                estimatedArrivalTime = tp?.estimatedArrivalTime ?: Instant.now(),
//                deliveryStatus = ship.map { status ->
//                    DeliveryStatusDto(
//                        statusId = status.statusId ?:"",
//                        remarks = status.remarks ?: "",
//                        lastUpdated = status.lastUpdated ?: Instant.now()
//                    )
//                }
//            )
//        }
//   }




}