package TNT.SE_2024_02_TNT.service

import TNT.SE_2024_02_TNT.entity.Order
import TNT.SE_2024_02_TNT.repository.OrderRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import TNT.SE_2024_02_TNT.dto.OrderDtoRequest
import TNT.SE_2024_02_TNT.dto.OrderDtoSearch
import TNT.SE_2024_02_TNT.dto.SearchOrdersRequest
import TNT.SE_2024_02_TNT.repository.ContactRepository

@Transactional
@Service
class OrderService(val orderRepository: OrderRepository, val contactRepository: ContactRepository) {
    fun getAllOrderList(): List<OrderDtoSearch> {
        var orderDtoList: List<OrderDtoSearch>
        var orderList: List<Order> = orderRepository.findAll()
        orderList.forEach {
            val contact: Contact = contactRepository.
            OrderDtoSearch(
                it.orderId,

                )
        }
        return orderDtoList
    }

    fun createOrder(order: Order) {
        println("Create Order")
        orderRepository.save(order)
    }

    fun searchOrders(val request: SearchOrdersRequest) {
        var orderDtoList: List<OrderDtoRequest>

    }

    fun parseOrderRequest(orderRequest: Map<String, Any?>): Order {
        val order = Order()
        //todo : shipping info extraction
        //todo : Order Table data extraction
        //todo : return data
        return order
    }
}