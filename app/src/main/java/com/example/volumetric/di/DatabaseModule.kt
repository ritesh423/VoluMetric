package com.example.volumetric.di

import android.content.Context
import androidx.room.Room
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
    @Provides
    @Singleton
    fun provideWorkoutDatabase(@ApplicationContext context: Context): WorkoutDatabase {
        return Room.databaseBuilder(
            context,
            WorkoutDatabase::class.java,
            "workoutDatabase.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideWorkoutDetailDao(workoutDatabase: WorkoutDatabase): WorkoutDetailDao =
        workoutDatabase.workoutDetailDao()

}