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
class CardTaskEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private val id: UUID? = null
    private val text: String? = null
    private val isComplete = false

    @ManyToOne
    private val card: CardEntity? = null
}