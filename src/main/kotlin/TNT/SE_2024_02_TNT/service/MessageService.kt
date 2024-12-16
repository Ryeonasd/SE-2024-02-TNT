package TNT.SE_2024_02_TNT.service

import TNT.SE_2024_02_TNT.dto.*
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service

@Service
class MessageService {
    private val restTemplate = RestTemplate()

    fun sendMessage(messageServer: MessageDto) {
        val url = "http://192.168.219.103:8080/message/send"

        val headers = HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }

        val request = HttpEntity(messageServer, headers)
        restTemplate.exchange(url, HttpMethod.POST, request, String::class.java)
    }
}