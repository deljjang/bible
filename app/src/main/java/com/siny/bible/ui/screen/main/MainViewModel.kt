package com.siny.bible.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.siny.data.model.MainData
import com.siny.data.repository.MainRepository

class MainViewModel(
    repository: MainRepository,
    cd1: String,
) : ViewModel() {

    var mainList: List<MainData> = repository.getList(cd1)

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