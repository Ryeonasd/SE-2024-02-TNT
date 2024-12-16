package TNT.SE_2024_02_TNT.controller

import TNT.SE_2024_02_TNT.dto.*
import TNT.SE_2024_02_TNT.service.MessageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/message")
class MessageController(private val messageService: MessageService) {

    @PostMapping("/send")
    fun sendMessage(@RequestBody request: MessageDto): ResponseEntity<String> {
        return try {
            messageService.sendMessage(request)
            ResponseEntity("메시지 전송 완료", HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }  // 구글 문서의 json 양식에 맞게 보내는 api

    @PostMapping("/receive")
    fun receiveMessage(@RequestBody message: MessageDto): ResponseEntity<String> {
        // TODO: 받은 메시지 유효성 검증하고 DB에 저장하는 기능
        println(message.toString())
        val returnMsg: String = "수신완료"
        return ResponseEntity(returnMsg, HttpStatus.OK)
    }
}