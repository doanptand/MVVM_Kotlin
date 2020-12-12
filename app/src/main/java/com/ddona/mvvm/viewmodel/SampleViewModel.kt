package com.ddona.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddona.mvvm.util.Resource
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.Exception

class SampleViewModel : ViewModel() {
    private val data = MutableLiveData<Resource<List<String>>>()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            data.postValue(Resource.loading(null))
            try {
                withTimeout(100) {
                    data.postValue(
                        //we get data from network or database in here, but for sample, I return a string list
                        Resource.success(
                            listOf(
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                                "data1",
                            )
                        )
                    )
                }
            } catch (e: TimeoutCancellationException) {
                data.postValue(Resource.error("timeout exeption", null))
            } catch (e: Exception) {
                data.postValue(Resource.error("Some exception", null))
            }
        }
    }

    fun getData(): LiveData<Resource<List<String>>> {
        return data
    }
}