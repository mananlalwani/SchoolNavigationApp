package com.example.semester1project

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp

@Composable
fun ScheduleScreenLayout(
    modifier: Modifier,
    onSelectedIndexChange: (Int) -> Unit,
    roomList: List<Int>,
    onRoomListChange: (List<Int>) -> Unit) {
    // This variable stores a mutable list of the different room numbers
    var roomListLocal by remember { mutableStateOf(roomList) }
    // This variable is going to keep track of whether or not the dialog box is showing
    var showDialog by remember { mutableStateOf(false) }

    // This variable keeps track of the current roomNumber that is being added
    var currentRoomNumber by remember { mutableStateOf("Enter") }

    // This variable keeps track of the alert message when the room limit is exceeded
    var showAlertMessage by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Box(
            modifier = modifier
                .background(colorResource(R.color.beige))
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            RoomDisplayList(
                roomList = roomListLocal,
                onUpdateRoom = { index, newRoom ->
                    roomListLocal = roomListLocal.toMutableList().apply {
                        this[index] = newRoom
                    }
                    Log.d("ScheduleScreenLayout", "roomListLocal: $roomListLocal")
                    onRoomListChange(roomListLocal)
                    Log.d("ScheduleScreenLayout", "roomList: $roomList")
                },
                onDeleteRoom = { index ->
                    roomListLocal = roomListLocal.toMutableList().apply {
                        removeAt(index)
                    }
                    onRoomListChange(roomListLocal)
                }
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 60.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    AddButton(onClick = {
                        if (roomListLocal.size >= 8) {
                            showAlertMessage = true
                        } else {
                            showDialog = true
                        }
                    })
                }
                // Composes screen using button functions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom,

                    ) {

                    BackButton(onClick = { onSelectedIndexChange(0) })
                    NextButton(onClick = { onSelectedIndexChange(2) })

                }
            }


        }

        // Show alert message if the room limit is exceeded
        if (showAlertMessage) {
            AlertDialog(
                onDismissRequest = { showAlertMessage = false },
                title = { Text("Maximum Rooms Reached") },
                text = { Text("You can only add up to 8 rooms.") },
                confirmButton = {
                    Button(onClick = { showAlertMessage = false }) {
                        Text("Ok")
                    }
                }
            )
        }

        if (showDialog) {
            RoomNumberDialog(
                onDismiss = {
                    showDialog = false
                    currentRoomNumber = "Enter"
                },
                onSave = {
                    if (currentRoomNumber.isNotEmpty() && currentRoomNumber.all { it.isDigit() }
                        && currentRoomNumber.length <= 3 && currentRoomNumber > 199.toString() && currentRoomNumber
                        < 300.toString()) {
                        roomListLocal = roomListLocal.toMutableList().apply {
                            add(currentRoomNumber.toInt())
                        }
                        onRoomListChange(roomListLocal)
                        currentRoomNumber = "Enter" // Reset to default
                    }
                    showDialog = false
                },
                roomNumber = currentRoomNumber,
                onRoomNumberChange = { currentRoomNumber = it }
            )
        }
    }
}


@Composable
fun RoomNumberDialog(
    onDismiss: () -> Unit,
    onSave: (String) -> Unit,
    roomNumber: String,
    onRoomNumberChange: (String) -> Unit
) {
    /*
    // Checks to see if the dropdown box is expanded
    var expanded by remember { mutableStateOf(false) }

    // Options for non-room number classes
    val roomTypes = listOf("Library", "Auditorium", "Cafeteria")
    */

    // Alert pop-up when the 'Add Button' is pressed
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Enter Room Number") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = roomNumber,
                    onValueChange = { input ->

                        // Checks to see if the input is all digits and 3 or less numbers
                        if (input.all { it.isDigit() } && input.length <= 3) {
                            onRoomNumberChange(input)
                        }
                    },
                    label = { Text("Room Number") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),

                    // Change keyboard to all numbers
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),

                    // Code to clear text-box when interacted with
                    interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                        interactionSource.collectIsFocusedAsState().let { isFocused ->
                            if (isFocused.value && roomNumber == "Enter") {
                                onRoomNumberChange("")
                            }
                        }
                    },

                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = colorResource(id = R.color.darkBeige),
                        unfocusedContainerColor = colorResource(id = R.color.darkBeige),
                        disabledContainerColor = colorResource(id = R.color.darkBeige),
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black
                    )
                )

                /*
                // Icon for the dropdown menu
                IconButton(onClick = { expanded =!expanded }) {
                    Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Toggle dropdown",
                        tint = Color.Black)
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    roomTypes.forEachIndexed { index, type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                // Populate the text field with the special number assigned to non-room location
                                val newRoomNumber = "${index + 1}".padStart(3, '0')
                                onRoomNumberChange(newRoomNumber)
                                expanded = false
                            }
                        )
                    }
                }
                */
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (roomNumber.isNotEmpty() && roomNumber.all { it.isDigit() } && roomNumber.length <= 3) {
                        onSave(roomNumber)
                    }
                    // expanded = false
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.darkBeige),
                    contentColor = Color.Black
                ),
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor =
                colorResource(R.color.darkBeige), contentColor = Color.Black
                ),
            ) {
                Text("Cancel")
            }
        },

        shape = RoundedCornerShape(30.dp),

        modifier = Modifier
            .border(
                1.dp,
                colorResource(id = R.color.brown),
                RoundedCornerShape(30.dp)
            ) // Border properties
            .clip(RoundedCornerShape(30.dp)), // Ensure the border follows the shape

        containerColor = colorResource(R.color.beige),
        textContentColor = colorResource(R.color.darkBeige)
    )
}


@Composable
fun RoomDisplayList(roomList: List<Int>, onUpdateRoom: (Int, Int) -> Unit, onDeleteRoom: (Int) -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var editingRoomIndex by remember { mutableIntStateOf(-1) }
    var editingRoomText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        roomList.forEachIndexed { index, room ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(colorResource(R.color.darkBeige))
                    .border(1.dp, colorResource(R.color.brown), RoundedCornerShape(10.dp))
                    .padding(16.dp)
                    .height(37.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(50))
                            .background(colorResource(R.color.brown)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${index + 1}",
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Room Number: $room",
                        style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(onClick = {
                        isEditing = true
                        editingRoomIndex = index
                        editingRoomText = room.toString()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "Edit",
                            tint = Color.Black
                        )
                    }
                }
            }
        }

        if (isEditing) {
            AlertDialog(
                onDismissRequest = {
                    isEditing = false
                    editingRoomText = "" // Reset the editing text field
                },
                title = { Text("Edit Room") },
                text = {
                    TextField(
                        value = editingRoomText,
                        label = { Text("Room Number") },
                        onValueChange = { text ->
                            // Apply validation rules
                            if (text.all { it.isDigit() } && text.length <= 3) {
                                editingRoomText = text
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = colorResource(id = R.color.darkBeige),
                            unfocusedContainerColor = colorResource(id = R.color.darkBeige),
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black
                        )
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (editingRoomText.isNotEmpty()) {
                                onUpdateRoom(editingRoomIndex, editingRoomText.toInt())
                            }
                            isEditing = false
                            editingRoomText = "" // Reset editing state
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.darkGreen),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                onDeleteRoom(editingRoomIndex)
                                isEditing = false
                                editingRoomText = "" // Reset editing state
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.darkBeige),
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color(0xFFb04040)
                            )
                        }

                        Button(
                            onClick = {
                                isEditing = false
                                editingRoomText = "" // Reset editing state
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.darkBeige),
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Cancel")
                        }
                    }
                },
                containerColor = colorResource(R.color.beige),
                textContentColor = colorResource(R.color.darkBeige)
            )
        }
    }
}
@Composable
fun AddButton(onClick: () -> Unit) {
    AppButton(
        onClick = onClick,
        icon = Icons.Default.Add,
        description = "Add",
        buttonSize = 65.dp to 65.dp,
        iconScale = 1.5f
    )
}

@Composable
fun NextButton(onClick: () -> Unit) {
    AppButton(
        onClick = onClick,
        icon = Icons.Default.ArrowForward,
        description = "Next",
        buttonSize = 65.dp to 65.dp,
        iconScale = 1f
    )
}

@Composable
fun BackButton(onClick: () -> Unit) {
    AppButton(
        onClick = onClick,
        icon = Icons.Default.ArrowBack,
        description = "Next",
        modifier = Modifier,
        buttonSize = 65.dp to 65.dp,
        iconScale = 1f
    )
}

@Composable
fun AppButton(
    onClick: () -> Unit,
    icon: ImageVector,
    description: String,
    modifier: Modifier = Modifier,
    buttonSize: Pair<Dp, Dp> = 60.dp to 60.dp,
    iconScale: Float = 1.5f
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.darkBeige)),
        elevation = ButtonDefaults.elevatedButtonElevation(4.dp),
        modifier = modifier
            .width(buttonSize.first)
            .height(buttonSize.second)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.Black,
            modifier = Modifier.scale(iconScale)
        )
    }
}




