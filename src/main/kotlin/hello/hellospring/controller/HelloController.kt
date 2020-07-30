package hello.hellospring.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class HelloController {
    @GetMapping("hello")
    fun hello(model: Model): String {
        model.addAttribute("data", "hello!!")
        return "hello"
    }

    @GetMapping("hello-mvc")
    fun helloMvc(@RequestParam("name") name: String, model: Model): String {
        model.addAttribute("name", name)
        return "hello-template"
    }

    @GetMapping("hello-string")
    @ResponseBody
    fun helloString(@RequestParam("name") name: String): String {
        return "hello$name"
    }

    @GetMapping("hello-api")
    @ResponseBody
    fun helloApi(@RequestParam("name") name: String): Hello {
        return Hello(name)
    }

    class Hello(val name: String)
}