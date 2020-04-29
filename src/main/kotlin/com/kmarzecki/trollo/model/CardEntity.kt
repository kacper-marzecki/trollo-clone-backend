package com.kmarzecki.trollo.model

import lombok.*
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
data class CardEntity(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "id", updatable = false, nullable = false)
        val id: UUID? = null,
        val name: String,
        val description: String,
        val positionInLane: Int,
        @ManyToOne
        val lane: LaneEntity,
        @OneToMany
        var tasks: MutableSet<CardTaskEntity>,
        @OneToMany
        val files: MutableSet<FileEntity>
)