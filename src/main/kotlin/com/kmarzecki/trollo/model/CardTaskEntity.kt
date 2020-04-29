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
data class CardTaskEntity(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "id", updatable = false, nullable = false)
        val id: UUID? = null,
        var text: String,
        var isComplete: Boolean = false,
        @ManyToOne
        val card: CardEntity

)