package com.example.itc362_hw5_ex1c

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val IS_ANSWER_SHOWN_KEY = "IS_ANSWER_SHOWN_KEY"

class CheatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    var isAnswerShown: Boolean
        //use a getter and setter to get and set CURRENT_INDEX_KEY (if nothing set to false)
        get() = savedStateHandle.get(IS_ANSWER_SHOWN_KEY) ?: false
        set(value) = savedStateHandle.set(IS_ANSWER_SHOWN_KEY, value)

}