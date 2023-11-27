package com.example.android.volleysample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap

class TopViewModel: ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    /** これだけの用途だとmapでもいい */
    private val _transformedMessage = _message.switchMap {
        MutableLiveData<String>().apply {
            value = "$it World"
        }
    }
    val transformedMessage: LiveData<String>
        get() = _transformedMessage

    fun updateMessage(value: String) {
        _message.value = value
    }
}