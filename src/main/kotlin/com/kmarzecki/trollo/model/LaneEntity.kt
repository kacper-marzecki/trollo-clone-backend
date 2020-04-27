package com.kmarzecki.trollo.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
data class LaneEntity(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "id", updatable = false, nullable = false)
        val id: UUID? = null,
        @ManyToOne
        val board: BoardEntity,
        var name: String,
        var positionInBoard: Int = 0
)