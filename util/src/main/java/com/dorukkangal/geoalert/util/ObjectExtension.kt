package com.dorukkangal.geoalert.util

inline fun <A, R> whenNotNull(input: A?, block: (A) -> R): R? =
    input?.let(block)

inline fun <A, B, R> whenNotNull(first: A?, second: B?, block: (A, B) -> R): R? =
    when (null) {
        first, second -> null
        else -> block(first, second)
    }

inline fun <A, B, C, R> whenNotNull(first: A?, second: B?, third: C?, block: (A, B, C) -> R): R? =
    when (null) {
        first, second, third -> null
        else -> block(first, second, third)
    }
