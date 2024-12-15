package TNT.SE_2024_02_TNT.controller

import TNT.SE_2024_02_TNT.dto.*
import TNT.SE_2024_02_TNT.entity.ShipmentStatus
import TNT.SE_2024_02_TNT.service.DeliveryService
import jakarta.persistence.PostLoad
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody

import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/delivery")
class DeliveryController {
    @GetMapping("/statuslist")
    public ResponseEntity<List<ShipmentStatusDto>>getStatusList(@RequestParam("order_id") String orderId){

    }
    @PostMapping("/update")
    public ResponseEntity<String> updateStatus(@RequestBody ShipmentUpdateDto updateDto){

    }



}