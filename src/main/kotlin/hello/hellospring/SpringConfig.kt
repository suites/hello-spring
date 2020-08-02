package hello.hellospring

import JdbcMemberRepository
import hello.hellospring.repository.JdbcTemplateMemberRepository
import hello.hellospring.repository.MemberRepository
import hello.hellospring.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SpringConfig(private val dataSource: DataSource) {
    @Bean
    fun memberRepository(): MemberRepository {
        return JdbcTemplateMemberRepository(dataSource)
    }

    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository())
    }
}