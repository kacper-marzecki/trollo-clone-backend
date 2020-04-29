package com.kmarzecki.trollo.exception

class ElementNotFoundException(entityName: String, identifier: String)
    : RuntimeException("$entityName with id $identifier not found")
