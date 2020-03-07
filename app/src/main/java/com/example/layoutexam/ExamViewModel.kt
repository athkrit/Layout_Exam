package com.example.layoutexam

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.layoutexam.database.ExamDatabaseDao
import com.example.layoutexam.database.ExamScore
import kotlinx.coroutines.*

class ExamViewModel(
    val database: ExamDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _toast = MutableLiveData<String>()
    val toast
        get() = _toast

    private var questionIndex = 0
    private var _currentQuestion = MutableLiveData<ExamFragment.Question>()
    val currentQuestion
        get() = _currentQuestion

    private var _currentSpanCount = MutableLiveData<Int>()
    val currentSpanCount
        get() = _currentSpanCount

    private var choiceLayoutIndex = 0
    private var _currentChoice = MutableLiveData<MutableList<ExamFragment.Choice>>()
    val currentChoice
        get() = _currentChoice

    private var startTime = 0L
    private var stopTime = 0L
    var allAnswer =  ArrayList<String>(0)
    var allTimeUsed =  ArrayList<String>(0)
    fun onItemclick(iconName: String) {
        stopTime = System.currentTimeMillis()
        var time = stopTime - startTime
        if(isCorrect(iconName)){
            toast.value = "Correct $time mSec"
            allAnswer.add("Correct")
        } else{
            toast.value = "Wrong $time mSec"
            allAnswer.add("Correct")
        }
        allTimeUsed.add("$time mSec")
        questionIndex++
        setChoiceLayuout()
        setQuestion()
    }

    private fun isCorrect(iconName: String) =
        iconName.toLowerCase().equals(_currentQuestion.value?.question)

    fun clearToast() {
        toast.value = null
    }

    private fun insertData() {
        uiScope.launch {
            var examScore = ExamScore(0, allAnswer.toString(), allTimeUsed.toString())
            insert(examScore)
        }
    }

    private suspend fun insert(examScore: ExamScore) {
        withContext(Dispatchers.IO) {
            database.insert(examScore)
            allAnswer =  ArrayList<String>(0)
            allTimeUsed =  ArrayList<String>(0)
        }
    }

    private fun setQuestion() {
        if (questionIndex > 1) {
            questionIndex = 0
        }
        _currentQuestion.value = questions[questionIndex]
        startTime = System.currentTimeMillis()
    }

    private fun setChoiceLayuout() {
        if (questionIndex > 1) {
            choiceLayoutIndex++
            if(choiceLayoutIndex > 5){
                insertData()
                choiceLayoutIndex = 0
                questionIndex = 0
                _currentSpanCount.value = 2
            }
             else if(choiceLayoutIndex>2){
                _currentSpanCount.value = 3
            }
            _currentChoice.value = choiceLayout[choiceLayoutIndex]
        } else {
            _currentChoice.value?.shuffle()
        }
    }

    init {
        setQuestion()
        _currentChoice.value = choiceLayout[choiceLayoutIndex]
        _currentSpanCount.value = 2
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}