package com.example.volumetric.presentation.model

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

sealed class DateBucket(val label: String) {
    object Today : DateBucket("TODAY")
    object Yesterday : DateBucket("YESTERDAY")
    data class Earlier(val date: LocalDate) : DateBucket(
        date.format(
            DateTimeFormatter.ofPattern("EEE, dd MMM", Locale.getDefault())
        ).uppercase()
    )

    companion object {
        fun fromEpochMillis(
            millis: Long,
            today: LocalDate,
            zoneId: ZoneId
        ): DateBucket {
            val date = Instant.ofEpochMilli(millis)
                .atZone(zoneId)
                .toLocalDate()
            return when (date) {
                today -> Today
                today.minusDays(1) -> Yesterday
                else -> Earlier(date)
            }
        }
    }
}
