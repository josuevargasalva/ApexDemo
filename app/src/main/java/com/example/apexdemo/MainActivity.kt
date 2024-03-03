package com.example.apexdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.apexdemo.common.State
import com.example.apexdemo.models.MCharacter
import com.example.apexdemo.ui.theme.ApexDemoTheme
import com.example.apexdemo.viewmodel.CharacterViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.lang.Error

class MainActivity : ComponentActivity() {

    lateinit var viewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //Instancia del viewmodel y paso a estados para uso de jetpack
            viewModel = CharacterViewModel()
            val state by viewModel.state.observeAsState()
            val list by viewModel.currentData.observeAsState()
            viewModel.getData()

            ApexDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //Pintado de UI dependiendo del estado
                    if (state == State.LOADING) {
                        Progress()
                    }else if (state == State.ERROR) {
                        ErrorComponent()
                    }else {
                        if(list != null && !list!!.isEmpty()){
                            AllCharacters(list!!)
                        }else{
                            Empty()
                        }
                    }
                }
            }
        }
    }


    //Componente de carga
    @Composable
    fun Progress() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize())
        {
            CircularProgressIndicator(
                modifier = Modifier.then(Modifier.size(100.dp)),
                color = Color(0xffff0000),
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }

    //Componente de estado sin datos
    @Composable
    fun Empty() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize())
        {
            Text(text = "Sin elementos que mostrar")
        }
    }

    //Componente en caso de error que permite volver a intentar el request
    @Composable
    fun ErrorComponent() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize())
        {
            Text(text = "Error inesperado, lo sentimos")
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = {
                viewModel.getData()
            }) {
                Text(text = "Reintentar")
            }
        }
    }

    //componente que pinta todos los personajes
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AllCharacters(list: List<MCharacter>) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(list) { item ->
                CardComponent(item.name,item.status,item.species,item.image)
            }

        }

    }

    //componente de tarjeta que pinta el personaje
    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun CardComponent(name: String,status: String,species: String, image: String) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.medium,
        ) {



            Row (verticalAlignment = Alignment.CenterVertically){
                GlideImage(model = image, contentDescription = "")
                Column{
                    Text(text = name,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 10.dp))

                    Text(text = status,
                        modifier = Modifier
                            .padding(start =10.dp))

                    Text(text = species,
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 10.dp))

                }
            }




        }

    }
}