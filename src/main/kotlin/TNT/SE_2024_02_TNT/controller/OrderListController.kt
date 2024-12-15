package TNT.SE_2024_02_TNT.controller

import TNT.SE_2024_02_TNT.service.OrderListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/order")
class OrderListController(val orderListService: OrderListService) {

    @GetMapping("/list")
    fun getOrderList(): ResponseEntity<Map<String, Any?>> {
        return try {
            val orderList = orderListService.getAllOrders()
            val response: Map<String, Any?> = mapOf(
                "message" to "요청 성공",
                "orderList" to orderList
            )
            ResponseEntity(response, HttpStatus.OK)
        } catch (e: Exception) {
            val errorResponse: Map<String, Any?> = mapOf(
                "message" to e.message
            )
            ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
        }
    }
}