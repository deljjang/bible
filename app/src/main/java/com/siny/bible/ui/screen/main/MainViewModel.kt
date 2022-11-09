package com.siny.bible.ui.screen.main

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.siny.data.model.MainData
import com.siny.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository,
    private val cd1: String,
) : ViewModel() {

    private val tag = javaClass.simpleName

    var mainList: MutableList<MainData> = repository.getList(cd1)

    init {
        viewModelScope.launch {
            try {
                Log.d(tag, "$tag.init")
                mainList = repository.getList(cd1)
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }

}

class MainViewModelFactory(
    private val repository: MainRepository,
    private val cd1: String,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            repository = repository,
            cd1 = cd1
        ) as T
    }
}