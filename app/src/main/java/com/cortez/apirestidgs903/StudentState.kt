package com.cortez.apirestidgs903

data class StudentState(
    val students: List<Student> = emptyList(),
    val isLoading: Boolean = false,

)
