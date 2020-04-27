package com.kmarzecki.trollo.model

import com.kmarzecki.trollo.model.auth.UserEntity
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
data class BoardEntity(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "id", updatable = false, nullable = false)
        val id: UUID? = null,
        val name: String,

        @ManyToMany
        val users: List<UserEntity>,

        @OneToMany
        val lanes: List<LaneEntity>
)