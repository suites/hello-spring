package hello.hellospring.repository

import hello.hellospring.domain.Member
import java.util.*
import kotlin.collections.HashMap

class MemoryMemberRepository: MemberRepository {
    private val store = HashMap<Long, Member>()
    private var sequence = 0L

    override fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member
        return member
    }

    override fun findById(id: Long): Member? {
        return store[id]
    }

    override fun findByName(name: String): Member? {
        return store.values
                .find { member -> member.name == name }
    }

    override fun findAll(): List<Member> {
        return ArrayList(store.values)
    }

    fun clearStore() {
        store.clear()
    }
}