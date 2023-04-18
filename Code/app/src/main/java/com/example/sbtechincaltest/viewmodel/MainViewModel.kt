package com.example.sbtechincaltest.viewmodel

import android.text.Editable
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    fun isFieldTextBlank(text: Editable?): Boolean {
        return text.isNullOrBlank()
    }

}