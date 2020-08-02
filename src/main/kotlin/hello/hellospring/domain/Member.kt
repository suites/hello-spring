package hello.hellospring.domain

import javax.persistence.*

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String
)