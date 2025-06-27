package com.cortez.apirestidgs903

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun StudentScreen(viewModel: StudentViewModel) {
    val context = LocalContext.current
    val state = viewModel.state

    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(items = viewModel.response) { index, item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(Color.White)  // Corregí color.White por Color.White
            ) {
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = item.UserId.toString())
                    Text(text = item.Id.toString())
                    Text(text = item.Title)
                    Text(text = item.Body)
                }
            }
        }

        item {
            Button(
                onClick = {
                    val apiService = ApiService.getInstance()
                    val estudiante =
                        Student(UserId = 123, Title = "Mi titulo", Body = "Mi contenido")

                    apiService.addStudent(estudiante).enqueue(object : Callback<StudentResponse> {
                        override fun onResponse(
                            call: Call<StudentResponse>,
                            response: Response<StudentResponse>
                        ) {
                            if (response.isSuccessful) {
                                val resultado = response.body()

                                Toast.makeText(
                                    context,
                                    "Estudiante agregado correctamente",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "Error al agregar el estudiante",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        }

                        override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                            Toast.makeText(
                                context,
                                "Error al agregar el estudiante",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Agregar Estudiante")
            }
        }
    }
}