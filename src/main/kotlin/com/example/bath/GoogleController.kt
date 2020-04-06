package com.example.bath

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Message(val text: String, val priority: String)

@RestController
class MessageController {
//    @RequestMapping("/hello")
//    fun message(): Message {
//        return Message("Hello from Google Cloud", "High")
//    }

    @RequestMapping("/google")
    fun message(): Message {
        var gcv = GoogleCloudVision()
        val message = gcv.run()
        return Message(message, "High")
    }
}

