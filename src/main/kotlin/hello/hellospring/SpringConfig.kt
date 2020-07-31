package hello.hellospring

import hello.hellospring.repository.MemberRepository
import hello.hellospring.repository.MemoryMemberRepository
import hello.hellospring.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {
    @Bean
    fun memberRepository(): MemberRepository {
        return MemoryMemberRepository()
    }

    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository())
    }
}