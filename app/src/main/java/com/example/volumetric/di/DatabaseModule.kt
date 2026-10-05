package com.example.volumetric.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.volumetric.data.WorkoutDatabase
import com.example.volumetric.data.WorkoutDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                """
                CREATE TABLE IF NOT EXISTS workoutDetail_new (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    muscleGroup TEXT NOT NULL,
                    exerciseName TEXT NOT NULL,
                    totalSets INTEGER NOT NULL,
                    createdAt INTEGER NOT NULL
                )
                """.trimIndent()
            )
            db.execSQL(
                """
                INSERT INTO workoutDetail_new (
                    id, muscleGroup, exerciseName, totalSets, createdAt
                )
                SELECT id, muscleGroup, exerciseName, totalSets, createdAt
                FROM workoutDetail
                """.trimIndent()
            )
            db.execSQL("DROP TABLE workoutDetail")
            db.execSQL("ALTER TABLE workoutDetail_new RENAME TO workoutDetail")
        }
    }

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
                            """INSERT INTO workoutDetail (muscleGroup, exerciseName, totalSets, createdAt)
                               VALUES ('$muscleGroup', '$exercise', $sets, ${week13Timestamps[index]})"""
                        )
                    }
                }
            })
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Singleton
    @Provides
    fun provideWorkoutDetailDao(workoutDatabase: WorkoutDatabase): WorkoutDetailDao =
        workoutDatabase.workoutDetailDao()

}
