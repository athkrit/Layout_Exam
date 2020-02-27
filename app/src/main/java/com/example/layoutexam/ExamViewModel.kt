package com.example.layoutexam

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.layoutexam.database.ExamDatabaseDao
import com.example.layoutexam.database.ExamScore
import kotlinx.coroutines.*

class ExamViewModel (
    val database: ExamDatabaseDao,
    application: Application) : AndroidViewModel(application){

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _onClickEvent = MutableLiveData<String>()
    val clickEvent
        get() = _onClickEvent

    fun onItemclick(iconName:String){
        _onClickEvent.value = iconName
    }
    fun finish(){
        _onClickEvent.value = null
    }
    fun insertData(allAnswer:ArrayList<String>?,allTimeUsed:ArrayList<String>?){
        uiScope.launch {
            var examScore = ExamScore(0,allAnswer.toString(),allTimeUsed.toString())
            insert(examScore)
        }
    }
    private suspend fun insert(examScore: ExamScore) {
        withContext(Dispatchers.IO) {
            database.insert(examScore)
            database.getAllNights()
            Log.d("asdasd", "add "+database.getAllNights())
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}