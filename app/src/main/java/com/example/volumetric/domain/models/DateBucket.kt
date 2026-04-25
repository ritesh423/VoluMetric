package com.example.volumetric.domain.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

sealed class DateBucket(val label: String) {
    object Today : DateBucket("TODAY")
    object Yesterday : DateBucket("YESTERDAY")
    data class Earlier(val date: LocalDate) : DateBucket(
        date.format(DateTimeFormatter.ofPattern("EEE, dd MMM", Locale.getDefault())).uppercase()
    )

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun fromEpochMillis(millis: Long): DateBucket {
            val date = Instant.ofEpochMilli(millis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            val today = LocalDate.now()
            return when (date) {
                today -> Today
                today.minusDays(1) -> Yesterday
                else -> Earlier(date)
            }
        }
    }
}
