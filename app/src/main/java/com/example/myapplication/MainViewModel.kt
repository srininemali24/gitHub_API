package com.example.myapplication
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _repositoryList = mutableStateOf<List<Repository>>(emptyList())
    val repositoryList: State<List<Repository>> = _repositoryList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun fetchRepositories(username: String) {
        _isLoading.value = true
        _errorMessage.value = null

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = NetworkUtils.fetchRepositories(username)
                val repositories = parseRepositories(response)
                _repositoryList.value = repositories
            } catch (e: Exception) {
                _errorMessage.value = "Error fetching repositories"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
