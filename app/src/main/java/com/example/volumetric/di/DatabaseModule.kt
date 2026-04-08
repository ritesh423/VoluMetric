package com.example.volumetric.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.volumetric.data.WorkoutDatabase
import com.example.volumetric.data.WorkoutDetailDao
import com.example.volumetric.data.WorkoutDetailEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideWorkoutDatabase(@ApplicationContext context: Context): WorkoutDatabase {
        return Room.databaseBuilder(
            context,
            WorkoutDatabase::class.java,
            "workoutDatabase.db"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Seed week 13 (March 23-29, 2026) test data
                    // Timestamps: March 24 (Tue), March 25 (Wed), March 27 (Fri) at noon UTC
                    val week13Timestamps = listOf(
                        1774267200000L, // March 23, 2026 12:00 PM UTC (Monday)
                        1774353600000L, // March 24, 2026 12:00 PM UTC (Tuesday)
                        1774526400000L  // March 26, 2026 12:00 PM UTC (Thursday)
                    )
                    val seedData = listOf(
                        Triple("Chest", "Bench Press", 3),
                        Triple("Back", "Barbell Row", 3),
                        Triple("Legs", "Squat", 3)
                    )
                    seedData.forEachIndexed { index, (muscleGroup, exercise, sets) ->
                        db.execSQL(
                            """INSERT INTO workoutDetail (muscleGroup, exerciseName, totalSets, createdAt, weekOfYear, year)
                               VALUES ('$muscleGroup', '$exercise', $sets, ${week13Timestamps[index]}, 13, 2026)"""
                        )
                    }
                }
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideWorkoutDetailDao(workoutDatabase: WorkoutDatabase): WorkoutDetailDao =
        workoutDatabase.workoutDetailDao()

}