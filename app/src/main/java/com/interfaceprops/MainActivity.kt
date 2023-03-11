package com.interfaceprops

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.interfaceprops.camera.CameraCapture
import com.interfaceprops.ui.theme.InterfacePropsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterfacePropsTheme {
                MainComposable()
            }
        }
    }
}

val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")

@Composable
fun MainComposable(){
    CameraScreen()
}

@Composable
fun CameraScreen(){
    var imageUri by remember { mutableStateOf(EMPTY_IMAGE_URI) }

    if (imageUri != EMPTY_IMAGE_URI){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(model = imageUri, contentDescription = null)
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    imageUri = EMPTY_IMAGE_URI
                }
            ) {
                Text("Remove image")
            }
        }
    }
    else {
        CameraCapture(
            modifier = Modifier.fillMaxSize(),
            onImageFile = { file ->
                imageUri = file.toUri()
            }
        )
    }
}

@Composable
fun ImageDescriptionTest(){
    LazyColumn(
        modifier = Modifier.background(color = Color(73, 73, 73))
    ){
        item {
            Image(
                painter = painterResource(id = R.drawable.weld_1),
                contentDescription = null
            )
        }
    }

    var descriptionShowed  by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = if (!descriptionShowed) Arrangement.Top else Arrangement.Bottom,
        horizontalAlignment = if (!descriptionShowed) Alignment.End else Alignment.CenterHorizontally
    ) {
        if (descriptionShowed) {
            Card(
                modifier = Modifier
                    .padding(10.dp),
                shape = RoundedCornerShape(15.dp),
                elevation = 10.dp,
                backgroundColor = Color(0, 174, 239),
                contentColor = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Описание дефекта",
                            style = TextStyle(fontWeight = FontWeight(500), fontSize = 17.sp)
                        )
                        Icon(
                            modifier = Modifier.clickable { descriptionShowed = false },
                            imageVector = Icons.Rounded.Close,
                            contentDescription = null
                        )
                    }

                    Text (
                        text = "Сваренные детали смещены.\n",
                        style = TextStyle(fontSize = 14.sp)
                    )
                    Text(
                        text = "Выраженные особенности изображения",
                        style = TextStyle(fontWeight = FontWeight(500), fontSize = 17.sp)
                    )
                    Text (
                        text = "Выраженное различие почернения (оптической плотности) перпендикулярно сварному шву.\n",
                        style = TextStyle(fontSize = 14.sp)
                    )
                }
            }
        }
        else {
            FloatingActionButton(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .offset(x = 10.dp),
                onClick = { descriptionShowed = true },
                shape = RectangleShape,
                backgroundColor = Color.Transparent,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp)
            ) {
                Surface(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp),
                    color = Color(0, 174, 239),
                    contentColor = Color.White
                ){
                    Icon(
                        modifier = Modifier
                            .padding(5.dp),
                        painter = painterResource(id = R.drawable.description_2),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

