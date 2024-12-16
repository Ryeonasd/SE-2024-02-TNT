package TNT.SE_2024_02_TNT.controller

import TNT.SE_2024_02_TNT.dto.CustomsInfoRequestDto
import TNT.SE_2024_02_TNT.dto.InsuranceInfoRequestDto
import TNT.SE_2024_02_TNT.service.DocumentService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/document")
class DocumentController(var documentService: DocumentService) {

    @PostMapping("/addinsurance")
    fun addInsuranceRequest(@RequestBody request: InsuranceInfoRequestDto): ResponseEntity<String> {
        documentService.addInsuranece(request)
        return ResponseEntity.ok("보험 서류 작성 성공")
    }

    @PostMapping("/addcustoms")
    fun addCustomsRequest(@RequestBody request: CustomsInfoRequestDto): ResponseEntity<String> {
        documentService.addCustomsInfo(request)
        return ResponseEntity.ok("관세 서류 작성 성공")
    }
}