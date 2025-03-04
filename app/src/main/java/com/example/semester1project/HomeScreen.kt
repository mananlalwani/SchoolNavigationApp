package com.example.semester1project

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semester1project.ui.theme.mada
import com.example.semester1project.ui.theme.madaThin

@Composable
fun HomeScreenLayout(
    modifier: Modifier,
    onSelectedIndexChange: (Int) -> Unit,
    onRoomListChange: (List<Int>) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var quickRouteInput by remember { mutableStateOf("") }
    var quickRouteInput2 by remember { mutableStateOf("") }

    Box(modifier = modifier) {
        val beige = colorResource(R.color.beige)
        val darkGreen = colorResource(R.color.darkGreen)
        val brown = colorResource(R.color.brown)

        Image(
            painter = painterResource(R.drawable.school),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .padding(top = 70.dp)
                .scale(2.1f)
        )

        Canvas(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 32.dp)
        ) {
            drawCircle(
                color = beige,
                radius = 900f,
                center = Offset(size.width / 2, 1525f)
            )
        }

        Text(
            text = "FHS Navigator",
            color = darkGreen,
            textAlign = TextAlign.Center,
            fontFamily = mada,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 330.dp)
        )

        Canvas(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 350.dp)
        ) {
            drawLine(
                color = brown,
                start = Offset(-150f, 100f),
                end = Offset(150f, 100f),
                strokeWidth = 8f
            )
        }

        Button(
            onClick = { onSelectedIndexChange(1) },
            colors = ButtonDefaults.buttonColors(containerColor = beige),
            border = BorderStroke(3.dp, darkGreen),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 180.dp)
                .width(230.dp)
        ) {
            Text(
                text = "Schedule",
                color = darkGreen,
            )
        }

        Button(
            onClick = { onSelectedIndexChange(4) },
            colors = ButtonDefaults.buttonColors(containerColor = brown),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 300.dp)
                .width(230.dp)
        ) {
            Text(
                text = "Map",
                color = beige,
            )
        }

        Text(
            text = "Quick Route",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontFamily = madaThin,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 375.dp)
                .clickable {
                    showDialog = true
                }
        )

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .scale(.8f)
        )

        Text(
            text = "Created by Fremd students",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontFamily = madaThin,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        )
        var problem by remember { mutableStateOf(false) }
        // Alert Dialog for Quick Route Input
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Enter Room") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = quickRouteInput,
                            onValueChange = { quickRouteInput = it },
                            label = { Text("Start") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = quickRouteInput2,
                            onValueChange = { quickRouteInput2 = it },
                            label = { Text("End") },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (quickRouteInput.isEmpty() || quickRouteInput2.isEmpty()) {
                                problem = true
                                return@Button
                            } else if (quickRouteInput.toInt() !in 200..299 || quickRouteInput2.toInt() !in 200..299) {
                                problem = true
                                return@Button
                            }
                            showDialog = false // Close dialog
                            onSelectedIndexChange(3) // Go to MapScreen
                            Log.d("HomeScreenLayout", "quickRouteInput: $quickRouteInput")
                            Log.d("HomeScreenLayout", "quickRouteInput2: $quickRouteInput2")
                            onRoomListChange(listOf(quickRouteInput.toInt(), quickRouteInput2.toInt())) // Set roomList
                        },
                        colors = ButtonDefaults.buttonColors(containerColor =
                            colorResource(R.color.darkBeige), contentColor = Color.Black
                        )
                    ){
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor =
                        colorResource(R.color.brown), contentColor = Color.White
                        )
                    ){
                        Text("Dismiss")
                    }
                },

                containerColor = colorResource(R.color.darkGreen),
                textContentColor = colorResource(R.color.darkBeige)
            )

            if (problem){
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text(text = "Error") },
                    text = { Text(text = "Please enter a room number on floor 2") },
                    confirmButton = {
                        Button(
                            onClick = { problem = false },
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.darkBeige), contentColor = Color.Black)
                        ) { Text("OK") }
                    },
                    dismissButton = { },
                    containerColor = colorResource(R.color.darkGreen),
                    textContentColor = colorResource(R.color.darkBeige)

                )
            }
        }
    }
}
