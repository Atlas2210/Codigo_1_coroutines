package com.example.coroutinesapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class MainViewModel: ViewModel() {

    var resultState by mutableStateOf(
        ""
    )
        private set

    var countTime by mutableStateOf(
        0
    )
        private set


    private var oneCount by mutableStateOf(false)

    fun fetchData(){

        val job = viewModelScope.launch {

            for (i in 1..5) {
                delay(1000)
                countTime = 1
            }

            oneCount = true

        }

        viewModelScope.launch {
            delay(5000)
            resultState = "Respuesta Obtenida de la Web"
        }

        if(oneCount){
            job.cancel()
        }
    }

    //Esta función bloquea el hilo principal
    fun bloqueoApp(){
        Thread.sleep(5000)
        resultState = "Respuesta obtenida de la Web"
    }

}