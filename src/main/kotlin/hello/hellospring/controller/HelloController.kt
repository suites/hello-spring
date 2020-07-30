package hello.hellospring.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HelloController {
    @GetMapping("hello")
    fun hello(model: Model): String {
        model.addAttribute("data", "hello!!")
        return "hello"
    }
}