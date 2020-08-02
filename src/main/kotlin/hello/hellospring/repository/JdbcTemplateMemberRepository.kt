package hello.hellospring.repository

import hello.hellospring.domain.Member
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import java.util.*
import javax.sql.DataSource


class JdbcTemplateMemberRepository(
        private val dataSource: DataSource,
        private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)
): MemberRepository {
    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")
        val parameters: MutableMap<String, Any?> = HashMap()
        parameters["name"] = member.name
        val key = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(parameters))
        member.id = key.toLong()
        return member
    }

    override fun findById(id: Long): Member? {
        return jdbcTemplate.query("""select * from member where id = ?""", memberRowMapper(), id).firstOrNull()
    }

    override fun findByName(name: String): Member? {
        return jdbcTemplate.query("""select * from member where name = ?""", memberRowMapper(), name).firstOrNull()
    }

    override fun findAll(): List<Member> {
        return jdbcTemplate.query("""select * from member""", memberRowMapper())
    }

    private fun memberRowMapper(): RowMapper<Member> {
        return RowMapper { rs, _ ->
            Member(rs.getLong("id"), rs.getString("name"))
        }
    }

}