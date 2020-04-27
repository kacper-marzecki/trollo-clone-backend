package com.kmarzecki.trollo.util

import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * Collection utilities
 */
object CollectionUtils {
    /**
     * Convert an element to a Set of element
     *
     * @param x element
     * @param <T> type of element x
     * @return a set
    </T> */
    fun <T> asSet(x: T): Set<T> {
        return Stream.of(x).collect(Collectors.toSet())
    }

    /**
     * @param mapper function transforming elements of type A  to type B
     * @param xs     Collection A
     * @param <A> type of input element
     * @param <B> type of output element
     * @return List of B
    </B></A> */
    fun <A, B> mapList(mapper: (A)-> B, xs: Collection<A>): List<B> {
        return xs.stream()
                .map(mapper)
                .collect(Collectors.toList())
    }
}