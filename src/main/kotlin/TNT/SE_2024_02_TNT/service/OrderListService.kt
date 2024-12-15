package TNT.SE_2024_02_TNT.service

import TNT.SE_2024_02_TNT.repository.OrderListRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import TNT.SE_2024_02_TNT.dto.OrderProjection

@Transactional
@Service
class OrderListService(val orderListRepository: OrderListRepository) {

    fun getAllOrders(): List<OrderProjection> {
        return orderListRepository.findAllOrders().toList()
    }
}
