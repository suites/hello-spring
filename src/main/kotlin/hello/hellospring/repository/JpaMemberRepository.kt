package hello.hellospring.repository

import hello.hellospring.domain.Member
import javax.persistence.EntityManager

class JpaMemberRepository(private val entityManager: EntityManager): MemberRepository {
    override fun save(member: Member): Member {
        entityManager.persist(member)
        return member
    }

    override fun findById(id: Long): Member? {
        return entityManager.find(Member::class.java, id)
    }

    override fun findByName(name: String): Member? {
        return entityManager.createQuery("select m from Member m where m.name = :name", Member::class.java)
                .setParameter("name", name)
                .resultList
                .firstOrNull()
    }

    override fun findAll(): List<Member> {
        return entityManager.createQuery("select m from Member m", Member::class.java)
                .resultList
    }
}