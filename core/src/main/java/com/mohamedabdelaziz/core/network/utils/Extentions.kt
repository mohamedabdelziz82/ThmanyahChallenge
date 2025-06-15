package com.mohamedabdelaziz.core.network.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * @author: Mohamed Abdelaziz
 * @since Sat, 14 Jun 2025
 */
fun String.toRelativeTimeText(): String {
    val formats = listOf(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        "yyyy-MM-dd'T'HH:mm:ss'Z'"
    ).map { pattern ->
        SimpleDateFormat(pattern, Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
    }

    return try {
        val date = formats.firstNotNullOfOrNull { format ->
            try {
                format.parse(this)
            } catch (_: ParseException) {
                null
            }
        } ?: return this // fallback if all parsing fails

        val now = Date()
        val diffMillis = now.time - date.time

        val seconds = diffMillis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        val weeks = days / 7
        val months = days / 30
        val years = days / 365

        when {
            seconds < 60 -> "just now"
            minutes < 60 -> "before $minutes minute${if (minutes > 1) "s" else ""}"
            hours < 24 -> "before $hours hour${if (hours > 1) "s" else ""}"
            days < 7 -> "before $days day${if (days > 1) "s" else ""}"
            weeks < 4 -> "before $weeks week${if (weeks > 1) "s" else ""}"
            months < 12 -> "before $months month${if (months > 1) "s" else ""}"
            else -> "before $years year${if (years > 1) "s" else ""}"
        }
    } catch (e: Exception) {
        this
    }
}
fun Long.formatDuration(): String {
    val hrs = this / 3600
    val mins = (this % 3600) / 60
    val secs = this % 60

    return when {
        hrs > 0 && mins > 0 -> "$hrs hour${if (hrs > 1) "s" else ""} $mins minute${if (mins > 1) "s" else ""}"
        hrs > 0 -> "$hrs hour${if (hrs > 1) "s" else ""}"
        mins > 0 -> "$mins minute${if (mins > 1) "s" else ""}"
        else -> "$secs second${if (secs > 1) "s" else ""}"
    }
}
