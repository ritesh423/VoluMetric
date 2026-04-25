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
                    val week13Timestamps = listOf(
                        1774267200000L,
                        1774353600000L,
                        1774526400000L
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