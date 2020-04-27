package com.kmarzecki.trollo.exception

class ElementNotFountException(entityName: String, identifier: String)
    : RuntimeException("$entityName with id $identifier not found")
