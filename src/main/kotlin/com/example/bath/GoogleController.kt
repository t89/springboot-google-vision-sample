package com.example.bath

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Message(val text: String, val priority: String)

@RestController
class MessageController {
    @RequestMapping("/google")
    fun message(): Message {
        return Message("Hello from Google Cloud", "High")
    }
}

