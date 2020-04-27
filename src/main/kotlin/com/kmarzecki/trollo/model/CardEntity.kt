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
class CardEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private val id: UUID? = null
    private val name: String? = null
    private val description: String? = null
    private val positionInLane: Int? = null

    @ManyToOne
    private val lane: LaneEntity? = null

    @OneToMany
    private val tasks: List<CardTaskEntity>? = null

    @OneToMany
    private val files: List<FileEntity>? = null
}