package TNT.SE_2024_02_TNT.controller

import TNT.SE_2024_02_TNT.dto.ItemAssignDtoRequest
import TNT.SE_2024_02_TNT.service.ItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item")
class ItemController(
    private val itemService: ItemService
) {
    @PostMapping("/add")
    fun addItems(@RequestBody request: ItemAssignDtoRequest): ResponseEntity<Any> {
        itemService.addItemsToContainer(request)
        return ResponseEntity.ok(mapOf("message" to "저장 성공", "current_status" to "운송 준비 중"))
    }

    @PostMapping("/pop")
    fun deleteItems(@RequestBody request: Map<String, String>): ResponseEntity<Any> {
        val trackingNumber = request["tracking_number"] ?: return ResponseEntity.badRequest().build()
        itemService.deleteItems(trackingNumber)
        return ResponseEntity.ok(mapOf("message" to "삭제 성공"))
    }

    @GetMapping("/search")
    fun searchItems(@RequestBody request: Map<String, String>): ResponseEntity<Any> {
        val trackingNumber = request["tracking_number"] ?: return ResponseEntity.badRequest().build()
        val items = itemService.searchItems(trackingNumber)
        return ResponseEntity.ok(items)
    }

}
