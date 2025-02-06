package com.cookie.note.domain.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun isoTimestampToDate(isoTimeStamp: String): Date {
    val simpleDateTimeFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    SimpleDateFormat.getDateTimeInstance()
    simpleDateTimeFormatter.timeZone = TimeZone.getTimeZone("UTC")
    return simpleDateTimeFormatter.parse(isoTimeStamp)!!
}