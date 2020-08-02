package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemberRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest(
        @Autowired private val memberService: MemberService,
        @Autowired private val memberRepository: MemberRepository
) {
    @Test
    fun 회원가입() {
        // given
        val member = Member(name = "hello")

        // when
        val saveId = memberService.join(member)

        // then
        val findMember = memberRepository.findById(saveId)
        assertThat(member.name).isEqualTo(findMember?.name)
    }

    @Test
    fun 중복_회원_가입_예외() {
        // given
        val member1 = Member(name = "hello")
        val member2 = Member(name = "hello")

        // when
        memberService.join(member1)
        val e = assertThrows<IllegalStateException> { memberService.join(member2) }

        // then
        assertThat(e.message).isEqualTo("이미 존재하는 회원입니다.")
    }

    @Test
    fun 회원목록() {
        // given
        val member1 = Member(name = "hello1")
        val member2 = Member(name = "hello2")
        memberService.join(member1)
        memberService.join(member2)
        // when
        val members = memberService.findMembers()

        // then
        assertThat(members.size).isEqualTo(2)
    }
}