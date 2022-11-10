package com.siny.bible.ui.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.siny.bible.ui.screen.detail.tag
import com.siny.data.model.DetailData
import com.siny.data.model.MainData
import com.siny.data.repository.MainRepository

class MainViewModel(
    val repository: MainRepository,
    cd1: String,
) : ViewModel() {

    var mainList: List<MainData> = repository.getList(cd1)

    var detailList: List<DetailData>? = null

    fun getDetailList(mainData: MainData) {
        detailList = repository.getDetailList(mainData);
    }

    val cd3Count: (MainData) -> Int = {
        repository.getCd3Count(it)
    }

    val getPos: (DetailData) -> Int = {
        val pos = repository.getPos(it)
        Log.d("MainViewModel", "getPos=$it")
        Log.d("MainViewModel", "getPos.pos=$pos")

        repository.setMainPos(it.cd1, it.cd2, pos)
        pos
    }

    val setPos : (Int, Int, Int) -> Unit = {cd1, cd2, pos ->
        repository.setMainPos(cd1, cd2, pos)
    }

    val cd4Count: (Int, Int, Int) -> Int = {cd1, cd2, cd3 ->
        repository.getCd4Count(cd1, cd2, cd3)
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