package com.kmarzecki.trollo.model.auth

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Role(
        @Id
        @GeneratedValue
        val id: String? = null,
        val name: String
)