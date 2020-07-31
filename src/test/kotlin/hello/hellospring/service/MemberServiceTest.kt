package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemoryMemberRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class MemberServiceTest(
        private var memberService: MemberService,
        private var memberRepository: MemoryMemberRepository
) {
    @BeforeEach
    fun beforeEach() {
        memberRepository = MemoryMemberRepository()
        memberService = MemberService(memberRepository)
    }

    @AfterEach
    fun afterEach() {
        memberRepository.clearStore()
    }

    @Test
    fun 회원가입() {
        // given
        val member = Member(name = "hello")

        // when
        val saveId = memberService.join(member)

        // then
        val findMember = memberService.findOne(saveId)
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
}