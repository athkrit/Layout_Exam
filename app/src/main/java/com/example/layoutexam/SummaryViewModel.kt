package com.example.layoutexam

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.layoutexam.database.ExamDatabaseDao
import com.example.layoutexam.database.ExamScore
import kotlinx.coroutines.*

class SummaryViewModel (
    val database: ExamDatabaseDao,
    application: Application) : AndroidViewModel(application){

    val data =  database.getAllData()

}