package com.cortez.apirestidgs903

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel: ViewModel() {
    var state by mutableStateOf(StudentState())
        private set

    var response: List<Student> by mutableStateOf(emptyList())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )

            val apiService = ApiService.getInstance()
            val studentsList = apiService.getStudents()
            response = studentsList
            state = state.copy(
                isLoading = false,
                students = response
            )
        }
    }
}