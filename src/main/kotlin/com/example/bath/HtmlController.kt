package com.example.bath

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

    @GetMapping("/")
    fun bath(model: Model): String {
        model["title"] = "Roses are red, violets are blue, scan your bath, while you poo. \uD83D\uDCA9"
        return "bath"
    }

}
