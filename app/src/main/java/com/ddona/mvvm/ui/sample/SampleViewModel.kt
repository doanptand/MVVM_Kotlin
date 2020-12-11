package com.ddona.mvvm.ui.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleViewModel : ViewModel() {
    val listHistory: MutableLiveData<List<String>> = MutableLiveData()

    init {
        val data = listOf(
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
            "bac",
            "dsf",
        )
        listHistory.value = data
    }
}