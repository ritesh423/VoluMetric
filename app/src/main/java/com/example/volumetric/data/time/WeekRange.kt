package com.example.volumetric.data.time

import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters

data class WeekRange(
    val startMillis: Long,
    val endMillis: Long
) {
    fun previous(zoneId: ZoneId): WeekRange {
        val currentStartDate = Instant.ofEpochMilli(startMillis)
            .atZone(zoneId)
            .toLocalDate()
        val previousStartDate = currentStartDate.minusWeeks(1)
        return WeekRange(
            startMillis = previousStartDate
                .atStartOfDay(zoneId)
                .toInstant()
                .toEpochMilli(),
            endMillis = currentStartDate
                .atStartOfDay(zoneId)
                .toInstant()
                .toEpochMilli()
        )
    }

    companion object {
        fun containing(instant: Instant, zoneId: ZoneId): WeekRange {
            val date = instant.atZone(zoneId).toLocalDate()
            val startDate = date.with(
                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)
            )
            val endDate = startDate.plusWeeks(1)
            return WeekRange(
                startMillis = startDate
                    .atStartOfDay(zoneId)
                    .toInstant()
                    .toEpochMilli(),
                endMillis = endDate
                    .atStartOfDay(zoneId)
                    .toInstant()
                    .toEpochMilli()
            )
        }
    }
}
