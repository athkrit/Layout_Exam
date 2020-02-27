package com.example.layoutexam.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExamDatabaseDao {
    @Insert
    fun insert(examScore: ExamScore)

    @Query("SELECT * FROM exam_score_table ORDER BY userId DESC")
    fun getAllNights(): List<ExamScore>
}