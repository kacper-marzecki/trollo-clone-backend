package com.kmarzecki.trollo.util

import java.util.*

val UUID.str: String
    get() = this.toString()

fun Boolean.ifNotThen (fn: () -> Unit) {
    fn()
}

fun <T, K> List<T>.aggregateBy(keyFun: (T)-> K): Map<K, List<T>> {
    val map = mutableMapOf<K, MutableList<T>>()
    this.forEach {
        val key = keyFun(it)
        if(!map.containsKey(key)){
            map[key] = mutableListOf()
        }
        map.get(key)?.add(it)
    }
    return map
}