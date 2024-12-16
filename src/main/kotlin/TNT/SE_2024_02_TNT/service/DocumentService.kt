package TNT.SE_2024_02_TNT.service


import TNT.SE_2024_02_TNT.dto.*
import TNT.SE_2024_02_TNT.entity.*
import TNT.SE_2024_02_TNT.repository.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Transactional
@Service
class DocumentService(
    val orderRepository: OrderRepository,
    val insurenceRepository: InsurenceRepository,
    val customInfoRepository: CustomInfoRepository
    ) {
    fun addInsuranece(insuranceData : InsuranceInfoRequestDto): Boolean {
        val order = orderRepository.findByOrderId(insuranceData.order_id) ?: throw Exception("찾는 주문 정보가 없습니다.")
        val insurancedto = insuranceData.insurance_info
        val insurance = InsuranceInfo().apply {
            this.company = insurancedto.company
            this.premium = insurancedto.premium
            this.startDate = LocalDate.parse(insurancedto.start_date!!, DateTimeFormatter.ISO_DATE)
            this.endDate = LocalDate.parse(insurancedto.end_date!!, DateTimeFormatter.ISO_DATE)
            this.coverageAmount = insurancedto.coverage_amount
            this.coverageScope = insurancedto.coverage_scope
            this.policyNumber = insurancedto.policy_number
            this.order = order
        }

        insurenceRepository.save(insurance)
        return true
    }

    fun addCustomsInfo(customsData : CustomsInfoRequestDto): Boolean {
        val order = orderRepository.findByOrderId(customsData.order_id) ?: throw Exception("찾는 주문 정보가 없습니다.")
        val customsDto = customsData.customs_info
        val customs = CustomsInfo().apply {
            this.customsDeclarationNumber = customsDto.customs_declaration_number
            this.dutyAmount = customsDto.duty_amount
            this.ftaApplicable = customsDto.fta_applicable
            this.paymentMethod = customsDto.payment_method
            this.orderNumber = order
        }
        customInfoRepository.save(customs)
        return true
    }



}