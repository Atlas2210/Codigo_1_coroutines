package com.example.coroutinesapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coroutinesapp.viewmodel.MainViewModel

@Composable
fun CoroutinesApp(viewModel: MainViewModel, modifier: Modifier = Modifier){

    var changeColor by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            changeColor = !changeColor

        },
            colors = ButtonDefaults.buttonColors(
                if (changeColor ) Color.Blue else Color.Red
            )

        ) {

            Text("Cambio de color")
        }

        Spacer(modifier = Modifier.height(30.dp))
        Text("${viewModel.countTime} [s]")
        Text(viewModel.resultState)
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            viewModel.fetchData()
        }){
            Text("Realizar Consulta")
        }

    }

}

/*
@Preview(showBackground = true)
@Composable
fun CoroutinesApp_Preview(){
    CoroutinesApp()
}
 */