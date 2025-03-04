package com.example.semester1project

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.semester1project.ui.theme.madaThin
import kotlinx.coroutines.delay

@Composable
fun MapScreen(
    modifier: Modifier,
    onSelectedIndexChange: (Int) -> Unit,
    goFrom:Int,
    goTo:Int,
    quickRoute:Boolean,
    roomListQuick: List<Int>,
    onRoomListChangeQuick: (List<Int>) -> Unit,
    list : List<Int>,
    useList: Boolean
) {
    var start by remember { mutableIntStateOf(goFrom) }
    var end by remember { mutableIntStateOf(goTo) }
    var changeRoute by remember { mutableStateOf(false) }
    var showInstructions by remember { mutableStateOf(true) }
    var hide by remember { mutableStateOf(false) }
    var hideTime by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) { // show instructions for 3 seconds
        delay(3000)
        showInstructions = false
    }
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        if (useList) {
            Log.d("MapScreen", "list: $list")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Route: $list",
                    color = Color.Black,
                    fontFamily = madaThin,
                    fontSize = 20.sp
                )
            }
        }
        if (quickRoute) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Quick Route",
                    color = Color.Black,
                    fontFamily = madaThin,
                )
            }
        }
        if (start == 999 || end == 999) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = "Please select a start and end location",
                    color = Color.Black,
                    fontFamily = madaThin,
                )
            }
        }

        // Map
        if (!quickRoute) {
            CoilBitmap(roomToBlueNode(start), roomToBlueNode(end), onHideChange = { hide = it }, onTimeChange = { hideTime++ })
        } else {
            CoilBitmap(roomToBlueNode(roomListQuick[0]), roomToBlueNode(roomListQuick[1]), onHideChange = { hide = it }, onTimeChange = { hideTime++ })
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomStart),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            ButtonRow(
                onSelectedIndexChange = onSelectedIndexChange,
                quickRoute = quickRoute,
                onRoomListChangeQuick = onRoomListChangeQuick,
                onChangeRoute = { changeRoute = it },
                list = list
            )
        }

        //show instructions for 3 seconds
        if (showInstructions) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Double click on the map to zoom in",
                    color = Color.Black,
                    fontFamily = madaThin,
                )
                Text(
                    text = "Map will take a long time to load",
                    color = Color.Black,
                    fontFamily = madaThin,
                )
                Text(
                    text = "Please be patient",
                    color = Color.Black,
                    fontFamily = madaThin,
                )
                Text(
                    text = "Ignore any wait dialogs",
                    color = Color.Black,
                    fontFamily = madaThin,
                )
            }
        }
    }

    if (changeRoute) {
        ChangeRouteDialog(
            onChangeRoute = { changeRoute = it },
            list = list,
            onStartChange = { start = it },
            onEndChange = { end = it }
        )
    }
}

@Composable
fun ChangeRouteDialog(
    onChangeRoute: (Boolean) -> Unit,
    list: List<Int>,
    onStartChange: (Int) -> Unit,
    onEndChange: (Int) -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("Click Here") }
    var val1 by remember { mutableIntStateOf(0) }
    var val2 by remember { mutableIntStateOf(0) }
    AlertDialog(
        onDismissRequest = {
            onChangeRoute(false)
        },
        title = {
            Text(text = "Select a new route")
        },
        text = {
            //select new route in dropdown
            Row() {
                Text(text = text)
            }
            // Icon for the dropdown menu
            Row {
                Column(
                    modifier = Modifier.fillMaxWidth().clickable { openDialog = !openDialog },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
//                    IconButton(
//                        onClick = { openDialog = !openDialog },
//                        modifier = Modifier.align(Alignment.Start)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.AddCircle,
//                            contentDescription = "Toggle dropdown",
//                            tint = Color.Black,
//                        )
//                    }

                    DropdownMenu(
                        expanded = openDialog,
                        onDismissRequest = { openDialog = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.End),
                    ) {
                        list.forEachIndexed { index, type ->
                            if (index <= list.size - 2) {
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(list[index].toString())
                                            Text(" to ")
                                            Text(list[index + 1].toString())
                                        }
                                    },
                                    onClick = {
                                        openDialog = false
                                        text = "Route changed to $type to ${list[index + 1]}"
                                        val1 = type
                                        val2 = list[index + 1]
                                    }
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onChangeRoute(false)
                    onStartChange(val1)
                    onEndChange(val2)

                },
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(
                onClick = { onChangeRoute(false) },
            ) {
                Text("Cancel")
            }
        },
        containerColor = colorResource(R.color.darkGreen),
        textContentColor = colorResource(R.color.darkBeige)
    )
}

@Composable
fun ButtonRow(
    onSelectedIndexChange: (Int) -> Unit,
    quickRoute: Boolean,
    onRoomListChangeQuick: (List<Int>) -> Unit,
    onChangeRoute: (Boolean) -> Unit,
    list: List<Int>
) {
    val beige = colorResource(R.color.beige)
    val green = colorResource(R.color.darkGreen)
    val buttonColor = ButtonDefaults.buttonColors(contentColor = green, containerColor = beige)

    if (quickRoute) {
        Row {
            Column {
                // Buttons
                BackButton {
                    onSelectedIndexChange(0)
                    onRoomListChangeQuick(emptyList())
                }
            }
        }
    } else {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                // Buttons
                BackButton {
                    onSelectedIndexChange(1)
                }
            }
            if (list.size >= 2) {
            Column {
                //Change Route Button
                Button(
                    onClick = {
                        onChangeRoute(true)
                    },
                    colors = buttonColor,
                    border = BorderStroke(2.dp, green),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(
                        text = "Change Route",
                        color = green,
                        fontFamily = madaThin,
                        fontSize = 20.sp
                    )
                }
            }}
        }
    }
}

