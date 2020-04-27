package com.kmarzecki.trollo.exception

data class OperationNotPermittedException(val user: String?=null , val customMessage: String? = null) : RuntimeException()