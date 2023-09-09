package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.leanback.widget.SearchBar
import androidx.leanback.widget.SearchBar.SearchBarListener
import com.example.myapplication.ui.theme.MyApplicationTheme

class Contactos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}


@Composable
fun Greeting() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_menu_24),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp),
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Contactos",
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )
        }

        MainScreen()
        Spacer(modifier = Modifier.height(16.dp))
        ContactosList() 
    }

}


data class Contacto(val nombre: String, val imagenResId: Int)

@Composable
fun ContactosList() {

    val listaDeContactos = listOf(
        Contacto("Banco Azteca", R.drawable.bancoaztecaaa),
        Contacto("Banco Banrural", R.drawable.bancobanrural),
        Contacto("Banco Industrial", R.drawable.bancobi),
        Contacto("Banco G&T", R.drawable.bancog_t)
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(listaDeContactos) { contacto ->
            ContactoItem(contacto = contacto)
        }
    }
}

@Composable
fun ContactoItem(contacto: Contacto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp).background(Color.White),
        shape = MaterialTheme.shapes.medium,

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = contacto.imagenResId),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFFE66FB5),
                                Color(0xFF6F85E6)
                            )
                        )
                    ),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = contacto.nombre,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        mutableStateListOf(
            "Banco Industrial",
            "Banco G&T"
        )
    }
    Scaffold {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text ,
            onQueryChange = {
                text = it
            } ,
            onSearch = {
                items.add(text)
                active = false
                text = ""
            },
            active = active ,
            onActiveChange ={
                active = it
            },
            placeholder = {
                Text(text = "Buscar contacto")
            },
            leadingIcon = {
              Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                           if(text.isNotEmpty()) {
                               text = ""
                           } else {
                            active= false
                           }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon"
                    )
                }
            }
        ) {
    items.forEach{
        Row (
            modifier = Modifier.padding(all = 14.dp)
        ){
            Icon(
                modifier = Modifier.padding(end = 10.dp),
                imageVector = Icons.Default.History, contentDescription = "Historial " 
            )
            Text(text = it)
        }
    }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting()
    }
}