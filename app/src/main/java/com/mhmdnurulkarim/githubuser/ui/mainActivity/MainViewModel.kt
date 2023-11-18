package com.mhmdnurulkarim.githubuser.ui.mainActivity

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhmdnurulkarim.githubuser.data.Repository
import com.mhmdnurulkarim.githubuser.data.Result
import com.mhmdnurulkarim.githubuser.data.model.FakeNameData
import com.mhmdnurulkarim.githubuser.data.network.DetailUserResponse
import com.mhmdnurulkarim.githubuser.data.network.SearchResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _queryUser = mutableStateOf("")
    val queryUser: State<String> get() = _queryUser

    fun getUser(query: String) = repository.searchUser(query)

    fun searchUser(newQuery: String) {
        viewModelScope.launch {
            _queryUser.value = newQuery
        }
    }
}