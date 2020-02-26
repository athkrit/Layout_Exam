package com.example.layoutexam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class ExamViewModel (application: Application) : AndroidViewModel(application){

    private var _onClickEvent = MutableLiveData<String>()
    val clickEvent
        get() = _onClickEvent

    fun onItemclick(iconName:String){
        _onClickEvent.value = iconName
    }
    fun finish(){
        _onClickEvent.value = null
    }

}