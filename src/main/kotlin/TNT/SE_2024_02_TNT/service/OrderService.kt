package TNT.SE_2024_02_TNT.service

import TNT.SE_2024_02_TNT.dto.*
import TNT.SE_2024_02_TNT.entity.*
import TNT.SE_2024_02_TNT.repository.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.Instant

@Transactional
@Service
class OrderService(val orderRepository: OrderRepository, val containerRepository: ContainerRepository, val shipmentStatusRepository: ShipmentStatusRepository) {
    fun getAllOrderList(): List<OrderDtoSearch> {
        val orderList: List<Order> = orderRepository.findAll()

        val orderDtoList: MutableList<OrderDtoSearch> = orderList.stream().map { o ->
            OrderDtoSearch(
                o.orderId,
                o.contacts.first().nameDepart,
                o.couplingOrdTrs.first().idTr?.addressDepart,
                o.couplingOrdTrs.first().idTr?.addressDest,
                o.shipmentStatuses.first().currentStatus,
                o.containers.first().containerId,
                o.couplingOrdTrs.first().idTr?.pickupTime,
                o.couplingOrdTrs.first().idTr?.estimatedArrivalTime
            )
        }.toList()
        return orderDtoList
    }

    fun createOrder(request: OrderDtoRequest): OrderCreateResponseDto {
        // Order 엔티티 생성 및 저장
        val order = Order(
            orderId = request.orderId,
            trackingNumber = request.orderInfo.shippingInfo.trackingNumber,
            containerNumber = request.containerId,
            relay = request.relay
        )
        orderRepository.save(order)

        // OrderTPInfo 엔티티 생성 및 저장
        val shippingInfo = request.orderInfo.shippingInfo
        val orderTPInfo = OrderTPInfo().apply {}
        orderTPInfoRepository.save(orderTPInfo)

        // Container 엔티티 생성 및 저장
        val container = Container().apply {
            containerId = request.containerId
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
            order.contacts.first().nameDepart,
            order.couplingOrdTrs.first().idTr?.addressDepart,
            order.couplingOrdTrs.first().idTr?.addressDest,
            order.shipmentStatuses.first().currentStatus,
            order.containers.first().containerId,
            order.couplingOrdTrs.first().idTr?.pickupTime,
            order.couplingOrdTrs.first().idTr?.estimatedArrivalTime
        )
    }

    // container_id로 주문 검색
    fun searchOrderByContainerId(containerId: String): OrderDtoSearch? {
        val container = containerRepository.findByContainerId(containerId) ?: throw Exception("찾는 컨테이너 정보가 없습니다.")
        val order = container.order!!
        return OrderDtoSearch(
            order.orderId,
            order.contacts.first().nameDepart,
            order.couplingOrdTrs.first().idTr?.addressDepart,
            order.couplingOrdTrs.first().idTr?.addressDest,
            order.containers.first().containerId,
            order.shipmentStatuses.first().currentStatus,
            order.couplingOrdTrs.first().idTr?.pickupTime,
            order.couplingOrdTrs.first().idTr?.estimatedArrivalTime
        )
    }



    // 운송 현황 상태 DB에서 가져오기
    fun getShipmentStatusList(orderId: String): List<ShipmentStatusDtoSearch> {
        val order = orderRepository.findByOrderId(orderId)
        val shipmentStatuses = order?.shipmentStatuses ?: throw Exception()
        return shipmentStatuses.map { shipmentStatus ->
            ShipmentStatusDtoSearch(
                statusId = shipmentStatus.statusId?.toString(),
                order = shipmentStatus.order!!, // 연관된 Order 객체
                currentLocation = shipmentStatus.currentLocation,
                currentStatus = shipmentStatus.currentStatus,
                lastUpdated = shipmentStatus.lastUpdated ?: Instant.now(), // null 처리
                remarks = shipmentStatus.remarks,
                transportVehicleNum = shipmentStatus.transportVehicleNum
            )
        }
    }


    // 변경한 운송현황 값 DB에 갖다넣기
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

    fun getOrderDetails(orderId: String): OrderDetailDto? {
        val order = orderRepository.findById(orderId).orElse(null)
        val item = itemRepository.findByOrderId(orderId).firstOrNull()
        val ship = shipmentStatusRepository.findByOrderId(orderId)
        val tp = transportInfoRepository.findByTrackingNumber(order.trackingNumber ?: throw IllegalArgumentException("Tracking number not found"))
        val contact = ContactRepository.findByIdOrder(orderId).orElse(null)

        return order?.let {
            OrderDetailDto(
                orderId = it.orderId ?: "",
                nameDepart = contact?.nameDepart ?: "",
                userId = it.userId ?: "",
                weight = item?.weight ?: 0.0,
                hsCode = item?.hsCode?: "",
                itemName = item?.name ?: "",
                price = item?.price?.toInt() ?: 0,
                originAddress = tp?.originAddress ?: "",
                destinationAddress = tp?.destinationAddress ?: "",
                trackingNumber = it.trackingNumber ?: "",
                estimatedArrivalTime = tp?.estimatedArrivalTime,
                deliveryStatus = ship.map { status ->
                    DeliveryStatusDto(
                        statusId = status.statusId ?:"",
                        remarks = status.remarks ?: "",
                        lastUpdated = status.lastUpdated ?: Instant.now()
                    )
                }
            )
        }
    }




}