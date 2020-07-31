package hello.hellospring.controller

import hello.hellospring.service.MemberService
import org.springframework.stereotype.Controller

@Controller
class MemberController (
    private var memberService: MemberService
) {

}