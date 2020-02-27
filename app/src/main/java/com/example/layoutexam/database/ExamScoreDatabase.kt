package com.example.layoutexam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ExamScore::class], version = 1, exportSchema = false)
abstract class ExamScoreDatabase : RoomDatabase() {

    abstract val examDatabaseDao: ExamDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ExamScoreDatabase? = null

        fun getInstance(context: Context): ExamScoreDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ExamScoreDatabase::class.java,
                        "exam_score_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
