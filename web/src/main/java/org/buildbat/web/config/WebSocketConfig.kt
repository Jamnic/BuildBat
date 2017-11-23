package org.buildbat.web.config

import org.buildbat.filesystem.file.BaseFile
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.socket.*
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import java.io.BufferedReader
import java.io.FileReader

// TODO to further elaboration
@Configuration
@Controller
@EnableWebSocket
open class WebSocketConfig : WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(myHandler(), "myHandler")
                .setAllowedOrigins("*")
    }

    @Bean
    open fun myHandler(): WebSocketHandler {
        return object : WebSocketHandler {
            override fun handleTransportError(p0: WebSocketSession?, p1: Throwable?) {
                println("ERROR")
            }

            override fun afterConnectionClosed(p0: WebSocketSession?, p1: CloseStatus?) {
                println("CLOSED")
            }

            override fun handleMessage(session: WebSocketSession?, message: WebSocketMessage<*>?) {
                message as TextMessage
                session as StandardWebSocketSession

                val br = BufferedReader(FileReader(BaseFile(message.payload).realFile()))

                while (true) {
                    br.lines().forEach {
                        session.sendMessage(TextMessage(it.toByteArray()))
                    }
                }
            }

            override fun afterConnectionEstablished(p0: WebSocketSession?) {
                println("CONNECTED")
            }

            override fun supportsPartialMessages(): Boolean {
                return true
            }
        }
    }
}
