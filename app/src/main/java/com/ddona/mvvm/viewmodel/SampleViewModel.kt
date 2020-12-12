package com.ddona.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddona.mvvm.util.Resource
import com.ddona.mvvm.util.Result
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.lang.Exception

class SampleViewModel : ViewModel() {
    private val data = MutableLiveData<Resource<List<String>>>()
    private val localData = MutableLiveData<Result>()

    init {
        fetchData()
        fetchLocalData()
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

    private fun fetchLocalData() {
        viewModelScope.launch {
            localData.postValue(Result.Loading)
            try {
                withTimeout(1000) {
                    localData.postValue(Result.Success(listOf("asdf", "asdf", "ASdf")))
                }
            } catch (e: Exception) {
                localData.postValue(Result.Error(e))
            }
        }

    }

    fun getLocalData(): LiveData<Result> = localData


    fun getData(): LiveData<Resource<List<String>>> = data

}