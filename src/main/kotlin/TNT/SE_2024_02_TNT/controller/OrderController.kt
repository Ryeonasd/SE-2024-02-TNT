package TNT.SE_2024_02_TNT.controller

import TNT.SE_2024_02_TNT.dto.*
import TNT.SE_2024_02_TNT.entity.Order
import TNT.SE_2024_02_TNT.service.OrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@Controller
@RequestMapping("/order")
class OrderController(var orderService: OrderService) {

    @GetMapping("/list")
    fun getOrderList(): ResponseEntity<Map<String, Any?>> {
        return try {
            val orderList = this.orderService.getAllOrderList()
            return ResponseEntity(mapOf(
                "message" to "요청 성공",
                "orderList" to orderList
            ), HttpStatus.OK)
        }
        catch (e: Exception) {
            ResponseEntity(mapOf("error" to e.message), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/create")
    fun createOrder(@RequestBody order: Order): ResponseEntity<String> {
        return try {
            this.orderService.createOrder(order)
            ResponseEntity("Order created successfully", HttpStatus.CREATED)
        }
        catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/search")
    fun searchOrders(@RequestBody request: SearchOrdersRequest): ResponseEntity<Map<String, Any>> {
        val searchList = this.orderService.searchOrders()
        return ResponseEntity(mapOf(
            "message" to "검색 성공",
            "orderList" to searchList
        ), HttpStatus.OK)
    }

    @GetMapping("/detail")
    fun orderDetails() {

    }

}