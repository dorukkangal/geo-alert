package com.dorukkangal.geoalert.util

import java.text.SimpleDateFormat
import java.util.*

fun calendar(): Calendar = Calendar.getInstance()

fun Date.format(dateFormat: DateFormat): String? {
    return SimpleDateFormat(dateFormat.format, Locale.getDefault())
        .format(this)
}

fun CharSequence.toDate(dateFormat: DateFormat): Date? {
    return SimpleDateFormat(dateFormat.format, Locale.getDefault())
        .parse(this.toString())
}

fun Number.toDate(): Date {
    return Date(toLong())
}

fun Date?.toCalendar(): Calendar {
    return Calendar.getInstance().also { calendar ->
        this@toCalendar?.let { calendar.time = this@toCalendar }
    }
}

fun Date.addYear(addition: Int): Date {
    return toCalendar().apply { add(Calendar.YEAR, addition) }.time
}

fun Calendar.setYear(value: Int): Calendar {
    return this.apply {
        set(Calendar.YEAR, value)
    }
}

fun Date.addMonth(addition: Int): Date {
    return toCalendar().apply { add(Calendar.MONTH, addition) }.time
}

fun Calendar.setMonth(value: Int): Calendar {
    return this.apply {
        set(Calendar.MONTH, value)
    }
}

fun Date.addDay(addition: Int): Date {
    return toCalendar().apply { add(Calendar.DATE, addition) }.time
}

fun Calendar.setDay(value: Int): Calendar {
    return this.apply {
        set(Calendar.DAY_OF_MONTH, value)
    }
}

fun Date.addHour(addition: Int): Date {
    return toCalendar().apply { add(Calendar.HOUR_OF_DAY, addition) }.time
}

fun Calendar.setHour(value: Int): Calendar {
    return this.apply {
        set(Calendar.HOUR_OF_DAY, value)
    }
}

fun Date.addMinute(addition: Int): Date {
    return toCalendar().apply { add(Calendar.MINUTE, addition) }.time
}

fun Calendar.setMinute(value: Int): Calendar {
    return this.apply {
        set(Calendar.MINUTE, value)
    }
}

fun Date.addSecond(addition: Int): Date {
    return toCalendar().apply { add(Calendar.SECOND, addition) }.time
}

fun Calendar.setSecond(value: Int): Calendar {
    return this.apply {
        set(Calendar.SECOND, value)
    }
}

/**
 * Compares this value with the specified value for order.
 * Returns zero if this value is equal to the specified other value,
 * a negative number if it's less than other or null,
 * or a positive number if it's greater than other or null.
 */
fun Calendar?.compareTime(calendar: Calendar?): Int {
    return when {
        this == null -> -1
        calendar == null -> 1
        this.get(Calendar.HOUR_OF_DAY) < calendar.get(Calendar.HOUR_OF_DAY) -> -1
        this.get(Calendar.HOUR_OF_DAY) == calendar.get(Calendar.HOUR_OF_DAY) -> {
            this.get(Calendar.MINUTE).compareTo(calendar.get(Calendar.MINUTE))
        }
        else -> 1
    }
}

enum class DateFormat(val format: String) {
    ISO_8601("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
}
