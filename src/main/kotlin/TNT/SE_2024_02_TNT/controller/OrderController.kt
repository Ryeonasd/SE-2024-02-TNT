package TNT.SE_2024_02_TNT.controller

import TNT.SE_2024_02_TNT.dto.*
import TNT.SE_2024_02_TNT.entity.*
import TNT.SE_2024_02_TNT.service.OrderService
import jakarta.servlet.http.HttpSession
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/order")
class OrderController(var orderService: OrderService) {

    @GetMapping("/list")
    fun getOrderList(): ResponseEntity<Map<String, Any?>> {
        return try {
            val orderList = this.orderService.getAllOrderList()
            ResponseEntity(mapOf(
                "message" to "요청 성공",
                "orderList" to orderList
            ), HttpStatus.OK)
        }
        catch (e: Exception) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/create")
    fun createOrder(@RequestBody OrderDtoRequest: OrderDtoRequest): ResponseEntity<String> {
        return try {
            this.orderService.createOrder(OrderDtoRequest)
            ResponseEntity("주문 생성 완료", HttpStatus.CREATED)
        }
        catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/search")
    fun searchOrder(@RequestBody request: OrderDtoSearch ): ResponseEntity<Map<String, Any?>> {
        return try {
            if (request.order_id == null && request.container_id == null) {
                ResponseEntity(
                    mapOf(
                        "message" to "검색 값이 비어있습니다."
                    ), HttpStatus.BAD_REQUEST
                )
            }
            var order: OrderDtoSearch? = null
            if (request.order_id != null) {
                order = orderService.searchOrderByOrderId(request.order_id)
            }
            else if (request.container_id != null) {
                order = orderService.searchOrderByContainerId(request.container_id)
            }
            ResponseEntity(
                mapOf(
                    "message" to "검색 성공",
                    "order" to order
                ), HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.BAD_REQUEST)
        }
    }

//    @GetMapping("/detail")
//    fun getOrderDetails(@RequestParam orderId: String): ResponseEntity<OrderDetailDto> {
//        val orderDetails = orderService.getOrderDetails(orderId)
//        return if (orderDetails != null) {
//            ResponseEntity.ok(orderDetails)
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }

    // 운송 현황 가져 오기 (GET 요청)
    @GetMapping("/statuslist")
    fun getDeliveryStatusList(@RequestBody request: ShipmentStatusDtoSearch): ResponseEntity<Any> {
        return try {
            val orderId = request.order_id ?: throw IllegalArgumentException("order_id는 필수입니다.")

            // 서비스에서 데이터 가져오기
            val shipmentStatusList = orderService.getShipmentStatusList(orderId)

            if (shipmentStatusList.isEmpty()) {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    mapOf("message" to "해당 주문번호에 대한 운송 현황이 없습니다.")
                )
            } else {
                ResponseEntity.ok(
                    mapOf(
                        "header" to mapOf("Content-Type" to "application/json"),
                        "body" to shipmentStatusList
                    )
                )
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                mapOf("error" to e.message)
            )
        }
    }

    // 운송 현황 업데이트 (POST 요청)
    @PostMapping("/update")
    fun updateDeliveryStatus(@RequestBody request: Map<String, Any>): ResponseEntity<Map<String, String>> {
        return try {
            // 요청값 파싱
            val currentStatus = request["current_status"] as? String ?: "processing"
            val lastUpdated = request["last_updated"] as? String ?: throw IllegalArgumentException("last_updated는 필수입니다.")
            val remarks = request["remarks"] as? String ?: ""
            val transportId = request["transport_vehicle_num"] as? String ?: throw IllegalArgumentException("배송수단 관련한 id값은 필수입니다.")

            // 서비스에서 데이터 업데이트
            orderService.updateShipmentStatus(currentStatus, lastUpdated, remarks, transportId)

            ResponseEntity.status(HttpStatus.CREATED).body(
                mapOf("message" to "잘됨.")
            )
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                mapOf("message" to "잘안됨: ${e.message}")
            )
        }
    }

}