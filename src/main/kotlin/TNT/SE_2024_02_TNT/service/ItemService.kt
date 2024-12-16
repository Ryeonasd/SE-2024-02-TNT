package TNT.SE_2024_02_TNT.service

import TNT.SE_2024_02_TNT.dto.*
import TNT.SE_2024_02_TNT.entity.Item
import TNT.SE_2024_02_TNT.entity.TransportInfo
import TNT.SE_2024_02_TNT.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(
    private val itemRepository: ItemRepository,
    private val transportInfoRepository: TransportInfoRepository
) {
    @Transactional
    fun addItemsToContainer(request: ItemAssignDtoRequest) {
        request.data.forEach { itemData ->  // ItemDataDto로 변경 반영
            val dimensions = "${itemData.x}*${itemData.y}*${itemData.z}"
            val item = Item(
                trackingNumber = itemData.tracking_number,
                name = itemData.name,
                isImported = itemData.is_imported,
                hsCode = itemData.hs_code,
                weight = itemData.weight,
                quantity = itemData.quantity,
                dimensions = dimensions,
                hazardous = itemData.hazardous,
                price = itemData.price
            )
            val transportInfo = TransportInfo(
                trackingNumber = itemData.tracking_number,
                originAddress = itemData.origin_address,
                destinationAddress = itemData.destination_address,
                pickupTime = itemData.pickup_time,
                estimatedArrivalTime = itemData.estimated_arrival_time
            )
            itemRepository.save(item)
            transportInfoRepository.save(transportInfo)
        }
    }


    @Transactional
    fun deleteItems(trackingNumber: String) {
        itemRepository.deleteByTrackingNumber(trackingNumber)
        transportInfoRepository.deleteByTrackingNumber(trackingNumber)
    }

    fun searchItems(trackingNumber: String) : Item? {
        return itemRepository.findByTrackingNumber(trackingNumber)

    }

}
