package com.example.layoutexam.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exam_score_table")
data class ExamScore(
    @PrimaryKey(autoGenerate = true)
    var userId:Long =0L,

    @ColumnInfo(name = "answer")
    var answers: String?,

    @ColumnInfo(name = "time_used")
    var timeUsed: String?
)