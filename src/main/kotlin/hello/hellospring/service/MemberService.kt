package hello.hellospring.service

import hello.hellospring.domain.Member
import hello.hellospring.repository.MemberRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
class MemberService (
        private val memberRepository: MemberRepository
) {
    /**
     * 회원 가입
     * @param member
     * @return
     */
    fun join(member: Member): Long {
        validateDuplicateMember(member)
        return memberRepository.save(member).id!!
    }

    private fun validateDuplicateMember(member: Member) {
        if(memberRepository.findByName(member.name) != null)
            throw IllegalStateException("이미 존재하는 회원입니다.")
    }

    /**
     *  전체 회원 조회
     */
    fun findMembers() : List<Member> {
        return memberRepository.findAll()
    }

    /**
     * 특정 회원 조회
     */
    fun findOne(memberId: Long) : Member? {
        return memberRepository.findById(memberId)
    }
}