package com.example.semester1project

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.semester1project.ui.theme.Semester1ProjectTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var selectedIndex by remember { mutableIntStateOf(0) }
            var roomList by remember { mutableStateOf(mutableListOf<Int>()) }
            var roomListQuick by remember { mutableStateOf(mutableListOf<Int>()) }
            val beige = colorResource(R.color.beige)
            Semester1ProjectTheme {
                CurrentScreen(
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = { newIndex -> selectedIndex = newIndex },
                    modifier = Modifier
                        .displayCutoutPadding()
                        .navigationBarsPadding()
                        .fillMaxSize()

                        .background(beige),
                    roomList = roomList,
                    onRoomListChange = { newRoomList -> roomList = newRoomList.toMutableList() },
                    roomListQuick = roomListQuick,
                    onRoomListChangeQuick = { newRoomList -> roomListQuick = newRoomList.toMutableList() },
                )
            }
        }
    }
}
@Composable
fun CurrentScreen(
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    roomList: List<Int>,
    onRoomListChange: (List<Int>) -> Unit,
    roomListQuick: List<Int>,
    onRoomListChangeQuick: (List<Int>) -> Unit
) {
    Log.d("CurrentScreen", "selectedIndex: $selectedIndex")
    Log.d("CurrentScreen", "roomList: $roomList")
    when (selectedIndex) {
        0 -> HomeScreenLayout(
            modifier = modifier,
            onSelectedIndexChange = onSelectedIndexChange,
            onRoomListChange = onRoomListChangeQuick
        )
        1 -> ScheduleScreenLayout(
            modifier = modifier,
            onSelectedIndexChange = onSelectedIndexChange,
            roomList = roomList,
            onRoomListChange = onRoomListChange
        )
        2 -> if (roomList.size >= 2) {
            MapScreen(
                modifier = modifier,
                onSelectedIndexChange = onSelectedIndexChange,
                goFrom = roomList[0],
                goTo = roomList[1],
                quickRoute = false,
                roomListQuick = roomListQuick,
                onRoomListChangeQuick = onRoomListChangeQuick,
                list = roomList,
                useList = true
            )
        } else {
            MapScreen(
                modifier = modifier,
                onSelectedIndexChange = onSelectedIndexChange,
                goFrom = 0,
                goTo = 0,
                quickRoute = false,
                roomListQuick = roomListQuick,
                onRoomListChangeQuick = onRoomListChangeQuick,
                list = roomList,
                useList = false
            )
        }
        3 -> MapScreen(
            modifier = modifier,
            onSelectedIndexChange = onSelectedIndexChange,
            goFrom = roomToBlueNode(roomListQuick[0]),
            goTo = roomToBlueNode(roomListQuick[1]),
            quickRoute = true,
            roomListQuick = roomListQuick,
            onRoomListChangeQuick = onRoomListChangeQuick,
            list = roomList,
            useList = false
        )
        4 -> MapScreen(
            modifier = modifier,
            onSelectedIndexChange = onSelectedIndexChange,
            goFrom = 0,
            goTo = 0,
            quickRoute = false,
            roomListQuick = roomListQuick,
            onRoomListChangeQuick = onRoomListChangeQuick,
            list = roomList,
            useList = false
        )
    }
}

fun roomToBlueNode(room: Int): Int {
    return when (room) {
        200, 201 -> 0
        253 -> 1
        251, 252 -> 2
        202, 203 -> 3
        254 -> 4
        255 -> 5
        204, 205 -> 6
        256, 257 -> 7
        250, 249 -> 8
        207, 206 -> 9
        258 -> 10
        209, 208 -> 11
        259 -> 12
        248, 247 -> 13
        211, 210 -> 14
        261, 260 -> 15
        246, 245 -> 16
        262, 263 -> 17
        213, 212 -> 218
        214 -> 19
        264 -> 20
        228 -> 21
        215 -> 22
        216 -> 23
        227 -> 24
        238 -> 26
        241 -> 27
        242 -> 28
        236, 237 -> 29
        239, 240 -> 30
        217 -> 31
        232, 222 -> 32
        226, 225 -> 33
        223 -> 34
        218 -> 35
        231 -> 36
        234, 220 -> 37
        229, 224 -> 38
        233, 221 -> 39
        219, 235 -> 40
        else -> 0
    }
}

