package hello.hellospring

import hello.hellospring.repository.MemberRepository
import hello.hellospring.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig(private val memberRepository: MemberRepository) {
    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository)
    }
}