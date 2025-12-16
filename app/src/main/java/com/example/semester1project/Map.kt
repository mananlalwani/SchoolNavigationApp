package com.example.semester1project

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.allowHardware
import coil3.request.crossfade
import coil3.size.Scale
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

@Composable
fun CoilBitmap(start: Int, destination: Int, onHideChange: ((Boolean) -> Unit)?, onTimeChange: (() -> Unit)?) {
    val request =
        ImageRequest.Builder(LocalContext.current).data(R.drawable.tl6gy1).scale(Scale.FIT)
            .crossfade(true).allowHardware(false).build()

    val painter = rememberAsyncImagePainter(request)
    var componentHeight by remember { mutableStateOf(0.dp) }
    var componentWidth by remember { mutableStateOf(0.dp) }

    var blueNodes by remember { mutableStateOf(emptyList<BlueNode>()) }
    var nodes by remember { mutableStateOf(emptyList<RedNode>()) }
    var obstacles by remember { mutableStateOf(emptyList<Obstacle>()) }
    var scaleW by remember { mutableFloatStateOf(0f) }
    var scaleH by remember { mutableFloatStateOf(0f) }
    val context = LocalContext.current

    scaleH = findScale(context.resources, componentHeight, componentWidth).second
    scaleW = findScale(context.resources, componentHeight, componentWidth).first

    val density = LocalDensity.current
    val beige = colorResource(id = R.color.beige)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .displayCutoutPadding()
            .background(beige)
        ,
        color = beige

        ) {

        Column(
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,

            ) {

            Box(
                modifier = Modifier
                    .zoomable(rememberZoomState())
                    .fillMaxSize()

            ) {
                if (!(start == 0 && destination == 0)) {

                Image(painter = painter,
                    contentDescription = "Image",
                    contentScale = ContentScale.Fit,

                    modifier = Modifier.onGloballyPositioned {
                        componentHeight = with(density) { it.size.height.toDp() }
                        componentWidth = with(density) { it.size.width.toDp() }
                    })

                LaunchedEffect(componentHeight, componentWidth) {
                    scaleH = findScale(context.resources, componentHeight, componentWidth).second
                    scaleW = findScale(context.resources, componentHeight, componentWidth).first
                    if (componentHeight > 0.dp && componentWidth > 0.dp) {
                        val (nodes1, blueNodes1, obstacles1) = findNodes(
                            context.resources, componentHeight, componentWidth
                        )
                        nodes = nodes1
                        blueNodes = blueNodes1
                        obstacles = obstacles1
                    }
                }
                if (nodes.isNotEmpty()) {
                    for (i in blueNodes.indices) {

                        if (i == 0){
                            blueNodes[i].rooms = listOf(200,201)
                        }
                        if (i == 1){
                            blueNodes[i].rooms = listOf(253,254)
                        }
                        if (i == 2){
                            blueNodes[i].rooms = listOf(251,252)
                        }
                        if (i == 3){
                            blueNodes[i].rooms = listOf(202,203)
                        }
                        if (i == 4){
                            blueNodes[i].rooms = listOf(254)
                        }
                        if (i == 5){
                            blueNodes[i].rooms = listOf(255)
                        }
                        if (i == 6) {
                            blueNodes[i].rooms = listOf(205,204)
                        }
                        if (i == 7){
                            blueNodes[i].rooms = listOf(256,257)
                        }
                        if (i == 8){
                            blueNodes[i].rooms = listOf(250,249)
                        }
                        if (i == 9){
                            blueNodes[i].rooms = listOf(207,206)
                        }
                        if (i == 10){
                            blueNodes[i].rooms = listOf(258,259)
                        }
                        if (i == 11){
                            blueNodes[i].rooms = listOf(209,208)
                        }
                        if (i == 12){
                            blueNodes[i].rooms = listOf((260),(261))
                        }
                        if (i == 13){
                            blueNodes[i].rooms = listOf((248),(247))
                        }
                        if (i == 14){
                            blueNodes[i].rooms = listOf((211),(210))
                        }
                        if (i == 15){
                            blueNodes[i].rooms = listOf((260),(261))
                        }
                        if (i == 16){
                            blueNodes[i].rooms = listOf((246),(245))
                        }
                        if (i == 17){
                            blueNodes[i].rooms = listOf((262),(263))
                        }
                        if (i == 18){
                            blueNodes[i].rooms = listOf((213),(212))
                        }
                        if (i == 19){
                            blueNodes[i].rooms = listOf((214))
                        }
                        if (i == 20){
                            blueNodes[i].rooms = listOf((264))
                        }
                        if (i == 21){
                            blueNodes[i].rooms = listOf((228))
                        }
                        if (i == 22){
                            blueNodes[i].rooms = listOf((215))
                        }
                        if (i == 23){
                            blueNodes[i].rooms = listOf((216))
                        }
                        if (i == 24){
                            blueNodes[i].rooms = listOf((227))
                        }

                        if (i == 26){
                            blueNodes[i].rooms = listOf((238))
                        }
                        if (i == 27){
                            blueNodes[i].rooms = listOf((241))
                        }
                        if (i == 28){
                            blueNodes[i].rooms = listOf((242))
                        }
                        if (i == 29){
                            blueNodes[i].rooms = listOf((236),(237))
                        }
                        if (i == 30){
                            blueNodes[i].rooms = listOf((239),(240))
                        }
                        if (i == 31){
                            blueNodes[i].rooms = listOf((217))
                        }
                        if (i == 32){
                            blueNodes[i].rooms = listOf((232),(222))
                        }
                        if (i == 33){
                            blueNodes[i].rooms = listOf((226),(225))
                        }
                        if (i == 34){
                            blueNodes[i].rooms = listOf((223),(231))
                        }
                        if (i == 35){
                            blueNodes[i].rooms = listOf((218))
                        }
                        if (i == 36){
                            blueNodes[i].rooms = listOf((231))
                        }
                        if (i == 37){
                            blueNodes[i].rooms = listOf((234),(220))
                        }
                        if (i == 38){
                            blueNodes[i].rooms = listOf((229),(224))
                        }
                        if (i == 39){
                            blueNodes[i].rooms = listOf((233),(221))
                        }
                        if (i == 40){
                            blueNodes[i].rooms = listOf((219),(235))
                        }

                    }
                    for (i in nodes.indices) {
                        nodes[i].id = i
                    }
//                    val start = (Math.random() * blueNodes.size).toInt()
//                    val destination = (Math.random() * nodes.size).toInt()
//                    val start = 28
//                    val destination = 0
                    var test = false
                    var test2 = false
                    var blueToRed = blueToNearestRed(nodes, blueNodes[start], obstacles)
                    if (findDistance(blueToRed, blueNodes[destination]) > findDistance(
                            blueToNearestRed(nodes,blueNodes[start],obstacles,blueToRed), blueNodes[destination]) ) {
                        val blueToRedTemp = blueToNearestRed(nodes,blueNodes[start],obstacles,blueToRed)
//                        if (!checkIfSameLineHorizontalOrVertical(blueNodes[start], blueToRed)) {
//                            if (findDistance(blueToRed, blueNodes[destination]) > findDistance(
//                                    blueToNearestRed(nodes,blueNodes[start],obstacles,blueToRed,blueToRedTemp), blueNodes[destination]) ) {
//                                blueToRed = blueToNearestRed(nodes,blueNodes[start],obstacles,blueToRed,blueToRedTemp)
//                            }
//                            } else {
                            blueToRed = blueToRedTemp
//                        }
                    }
                    var destinationToRed =
                        blueToNearestRed(nodes, blueNodes[destination], obstacles)
                    if (findDistance(destinationToRed, blueNodes[start]) > findDistance(
                            blueToNearestRed(nodes,blueNodes[destination],obstacles,destinationToRed), blueNodes[start])) {
                        val destinationToRedTemp = blueToNearestRed(nodes,blueNodes[destination],obstacles,destinationToRed)
//                        if (!checkIfSameLineHorizontalOrVertical(blueNodes[destination], destinationToRed)) {
//                            if (findDistance(destinationToRed, blueNodes[start]) > findDistance(
//                                    blueToNearestRed(nodes,blueNodes[destination],obstacles,destinationToRed,destinationToRedTemp), blueNodes[start])) {
//                                destinationToRed = blueToNearestRed(nodes,blueNodes[destination],obstacles,destinationToRed,destinationToRedTemp)
//                            }
//                        } else {
                            destinationToRed = destinationToRedTemp
//                        }
                    }
                    val destinationRedNodeNumber = nodes.indexOf(destinationToRed)
                    val path: List<Int>
                    if (checkIfSameLineHorizontalOrVertical(
                            blueNodes[start], blueNodes[destination]
                        ) && checkForObstacles(
                            blueNodes[start], blueNodes[destination], obstacles
                        )
                    ) {
                        path = listOf(start, destination)
                        test = false
                        test2 = true

                    } else if (checkIfSameLineHorizontalOrVertical(
                            blueNodes[start], blueNodes[djikstraAlgo(
                                nodes, blueToRed.id, destinationRedNodeNumber
                            )[0]]
                        )
                    ) {
                        path = djikstraAlgo(nodes, blueToRed.id, destinationRedNodeNumber)
                        test = false
                        test2 = false
                    } else {
                        path = djikstraAlgo(nodes, blueToRed.id, destinationRedNodeNumber)
                    }
                    if (onTimeChange != null) {
                        onTimeChange()
                    }
//                    val textMeasurer = rememberTextMeasurer()
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        var drawEnd = true
                        if (test) {
                            drawLine(
                                color = Color.Blue,
                                start = Offset(
                                    blueNodes[start].location.first,
                                    blueNodes[start].location.second
                                ),
                                end = Offset(blueToRed.location.first, blueToRed.location.second),
                                strokeWidth = 10f
                            )
                            for (i in 0 until path.size - 1) {
                                val (x1, y1) = nodes[path[i]].location
                                val (x2, y2) = nodes[path[i + 1]].location
                                drawLine(
                                    color = Color.Blue,
                                    start = Offset(x1, y1),
                                    end = Offset(x2, y2),
                                    strokeWidth = 10f
                                )
                            }
                            drawLine(
                                color = Color.Blue, start = Offset(
                                    nodes[destinationRedNodeNumber].location.first,
                                    nodes[destinationRedNodeNumber].location.second
                                ), end = Offset(
                                    blueNodes[destination].location.first,
                                    blueNodes[destination].location.second
                                ), strokeWidth = 10f
                            )
                        } else {
                            if (test2) {
                                drawLine(
                                    color = Color.Blue, start = Offset(
                                        blueNodes[start].location.first,
                                        blueNodes[start].location.second
                                    ), end = Offset(
                                        blueNodes[destination].location.first,
                                        blueNodes[destination].location.second
                                    ), strokeWidth = 10f
                                )
                            } else {
                                for (i in 0 until path.size - 1) {
                                    val (x1, y1) = nodes[path[i]].location
                                    val (x2, y2) = nodes[path[i + 1]].location
                                    if (checkIfSameLineHorizontalOrVertical(
                                            nodes[path[i + 1]], blueNodes[start]
                                        )
                                    ) {
                                        val (x3, y3) = blueNodes[start].location
                                        drawLine(
                                            color = Color.Blue,
                                            start = Offset(x2, y2),
                                            end = Offset(x3, y3),
                                            strokeWidth = 10f
                                        )
                                    } else if (checkIfSameLineHorizontalOrVertical(
                                            nodes[path[i]], blueNodes[destination]
                                        )
                                    ) {
                                        drawEnd = false
                                        val (x3, y3) = blueNodes[destination].location
                                        drawLine(
                                            color = Color.Blue,
                                            start = Offset(x1, y1),
                                            end = Offset(x3, y3),
                                            strokeWidth = 10f
                                        )
                                        break
                                    }
                                    else {
                                        drawLine(
                                            color = Color.Blue,
                                            start = Offset(x1, y1),
                                            end = Offset(x2, y2),
                                            strokeWidth = 10f
                                        )
                                    }
                                }
                                if (drawEnd) {
                                    drawLine(
                                        color = Color.Blue, start = Offset(
                                            nodes[destinationRedNodeNumber].location.first,
                                            nodes[destinationRedNodeNumber].location.second
                                        ), end = Offset(
                                            blueNodes[destination].location.first,
                                            blueNodes[destination].location.second
                                        ), strokeWidth = 10f
                                    )
                                }
                                drawLine(
                                    color = Color.Blue, start = Offset(
                                        blueNodes[start].location.first,
                                        blueNodes[start].location.second
                                    ), end = Offset(
                                        blueToRed.location.first, blueToRed.location.second
                                    ), strokeWidth = 10f
                                )
                            }
                        }
//                        for (i in blueNodes.indices) {
//                            drawCircle(
//                                color = Color.Blue, radius = 5f, center = Offset(
//                                    blueNodes[i].location.first.toFloat(),
//                                    blueNodes[i].location.second.toFloat()
//                                )
//                            )
////                            drawText(
////                                textMeasurer = textMeasurer, text = i.toString(), Offset(
////                                    blueNodes[i].location.first.toFloat(),
////                                    blueNodes[i].location.second.toFloat()
////                                ), TextStyle(color = Color.Red, fontSize = 4.sp)
////                            )
////
//                        }
                        if (test2) {
                            drawCircle(
                                color = Color.Blue, radius = 5f, center = Offset(
                                    blueNodes[start].location.first,
                                    blueNodes[start].location.second
                                )
                            )
                            drawCircle(
                                color = Color.Blue, radius = 5f, center = Offset(
                                    blueNodes[destination].location.first,
                                    blueNodes[destination].location.second
                                )
                            )
                        } else {
                            if (drawEnd){
                            for (i in path.indices) {
                                drawCircle(
                                    color = Color.Blue, radius = 5f, center = Offset(
                                        nodes[path[i]].location.first,
                                        nodes[path[i]].location.second
                                    )
                                )
                            }} else {
                                for (i in 0 until path.size - 1) {

                                    drawCircle(
                                        color = Color.Blue, radius = 5f, center = Offset(
                                            nodes[path[i]].location.first,
                                            nodes[path[i]].location.second
                                        )
                                    )
                                }
                            }
                        }
                        drawCircle(
                            color = Color.Magenta, radius = 10f, center = Offset(
                                blueNodes[start].location.first, blueNodes[start].location.second
                            )
                        )
                        drawCircle(
                            color = Color.Cyan, radius = 10f, center = Offset(
                                blueNodes[destination].location.first,
                                blueNodes[destination].location.second
                            )
                        )
//                        for (i in nodes.indices) {
//                            drawCircle(
//                                color = Color.Blue, radius = 10f, center = Offset(
//                                    nodes[i].location.first.toFloat(),
//                                    nodes[i].location.second.toFloat()
//                                )
//                            )
//                        }
//                        for (i in obstacles.indices) {
//                            drawCircle(
//                                color = Color.Black,
//                                radius = 1f,
//                                center = Offset(obstacles[i].location.first, obstacles[i].location.second)
//                            )
//                        }
//                        for (i in 0 until 500){
//                            drawCircle(
//                                color = Color.Magenta,
//                                radius = 10f,
//                                center = Offset(obstacles[i].location.first, obstacles[i].location.second)
//                            )
//                        }
                    }
                }
            } else {
                    Image(painter = painter,
                        contentDescription = "Image",
                        contentScale = ContentScale.Fit,

                        modifier = Modifier.onGloballyPositioned {
                            componentHeight = with(density) { it.size.height.toDp() }
                            componentWidth = with(density) { it.size.width.toDp() }
                        })
                }
            }
        }
    }

}

fun findNodes(
    context: Resources, height: Dp, width: Dp
): Triple<MutableList<RedNode>, MutableList<BlueNode>, MutableList<Obstacle>> {
    var nodes = mutableListOf<RedNode>()
    var blueNodes = mutableListOf<BlueNode>()
    val obstacles = mutableListOf<Obstacle>()
    val bitmap = BitmapFactory.decodeResource(context, R.drawable.aviocl)


    val screenHeight = height.value
    val scaleHeight: Float =
        screenHeight / (bitmap.height / Resources.getSystem().displayMetrics.density)
    val screenWidth = width.value
    val scaleWidth: Float =
        screenWidth / (bitmap.width / Resources.getSystem().displayMetrics.density)

    for (x in 0 until bitmap.width) {
        for (y in 0 until bitmap.height) {
            val pixel = bitmap.getPixel(x, y)
            val red = pixel.red
            val green = pixel.green
            val blue = pixel.blue
            if (red >= 250 && green <= 90 && blue <= 90) {

                val scaledX = (x * scaleWidth)
                val scaledY = (y * scaleHeight)

                nodes.add(RedNode(0, Pair(scaledX, scaledY)))

            } else if (red <= 90 && green <= 90 && blue >= 250) {
                val scaledX = (x * scaleWidth)
                val scaledY = (y * scaleHeight)

                blueNodes.add(BlueNode(0, Pair(scaledX, scaledY)))
            } else if (red <= 100 && green <= 100 && blue <= 100) {
                val scaledX = (x * scaleWidth)
                val scaledY = (y * scaleHeight)

                obstacles.add(Obstacle(0, Pair(scaledX, scaledY)))
            }
        }
    }
    nodes = filterNodes(nodes)
    nodes = alignNodes(nodes)
    blueNodes = filterNodes(blueNodes)
    blueNodes = alignNodes(blueNodes)
    return Triple(nodes, blueNodes, obstacles)
}

fun <T : Node> filterNodes(nodes: MutableList<T>): MutableList<T> {
    val filteredNodes = mutableListOf<T>()
    val threshold = 5
    for (i in nodes.indices) {
        val node = nodes[i]
        var found = false
        for (j in filteredNodes.indices) {
            val filteredNode = filteredNodes[j]
            if (abs(node.location.first - filteredNode.location.first) < threshold && abs(node.location.second - filteredNode.location.second) < threshold) {
                filteredNode.location = Pair(
                    (node.location.first + filteredNode.location.first) / 2,
                    (node.location.second + filteredNode.location.second) / 2
                )
                found = true
                break
            }
        }
        if (!found) {
            filteredNodes.add(node)
        }
    }
    return filteredNodes
}

fun <T : Node> alignNodes(nodes: MutableList<T>): MutableList<T> {
    val alignedNodes = mutableListOf<T>()
    val threshold = 10

    for (node in nodes) {
        var alignedX = node.location.first
        var alignedY = node.location.second

        for (otherNode in nodes) {
            if (node != otherNode) {
                if (abs(node.location.first - otherNode.location.first) < threshold) {
                    alignedX = (node.location.first + otherNode.location.first) / 2
                }
                if (abs(node.location.second - otherNode.location.second) < threshold) {
                    alignedY = (node.location.second + otherNode.location.second) / 2
                }
            }
        }
        alignedNodes.add(node.apply { location = Pair(alignedX, alignedY) })
    }

    return alignedNodes.distinctBy { it.location }.toMutableList()
}

fun <T : Node> djikstraAlgo(graph: List<T>, start: Int, end: Int): List<Int> {
    val nodes = mutableListOf<Int>()
    val edges = mutableListOf<Pair<Int, Int>>()
    for (i in graph.indices) {
        nodes.add(i)
    }

    var xMin = 0f
    var xMax = 0f
    var yMin = 0f
    var yMax = 0f
    for (i in graph) {
        val node = i.location
        xMin = min(xMin, node.first)
        xMax = max(xMax, node.first)
        yMin = min(yMin, node.second)
        yMax = max(yMax, node.second)
    }

    val corners = MutableList(4) {
        when (it) {
            0 -> Pair(xMin, yMin)
            1 -> Pair(xMin, yMax)
            2 -> Pair(xMax, yMin)
            3 -> Pair(xMax, yMax)
            else -> Pair(0, 0)
        }
    }

    for (i in graph.indices) {
        for (j in graph.indices) {
            if (i != j) {
                val node1 = graph[i]
                val node2 = graph[j]
                if (abs(node1.location.first - node2.location.first) < 10 || abs(node1.location.second - node2.location.second) < 10) {
                    if (!(corners.contains(node1.location) || corners.contains(node2.location))) {
                        edges.add(Pair(i, j))
                    } else {
                        break
                    }
                }
            }
        }
    }


    val distances = mutableMapOf<Int, Int>()
    val previous = mutableMapOf<Int, Int>()
    for (node in nodes) {
        distances[node] = Int.MAX_VALUE
        previous[node] = Int.MIN_VALUE
    }
    distances[start] = 0
    val unvisited = nodes.toMutableList()
    while (unvisited.isNotEmpty()) {
        val current = unvisited.minByOrNull { distances[it]!! }!!
        unvisited.remove(current)
        if (current == end) {
            break
        }
        for (edge in edges) {
            val (node1, node2) = edge
            if (node1 == current) {
                val newDistance = distances[current]!! + 1
                if (newDistance < distances[node2]!!) {
                    distances[node2] = newDistance
                    previous[node2] = current
                }
            }
        }
    }
    val path = mutableListOf<Int>()
    var current = end
    while (current != Int.MIN_VALUE) {
        path.add(current)
        current = previous[current]!!
    }
    return path.reversed()
}


fun <T : Node> checkIfSameLineHorizontalOrVertical(node1: T, node2: T): Boolean {
    val threshold = 10
    val (x1, y1) = node1.location
    val (x2, y2) = node2.location
    return abs(x1 - x2) < threshold || abs(y1 - y2) < threshold
}

fun findScale(context: Resources, height: Dp, width: Dp): Pair<Float, Float> {
    val bitmap = BitmapFactory.decodeResource(context, R.drawable.aviocl)


    val screenHeight = height.value
    val scaleHeight: Float =
        screenHeight / (bitmap.height / Resources.getSystem().displayMetrics.density)
    val screenWidth = width.value
    val scaleWidth: Float =
        screenWidth / (bitmap.width / Resources.getSystem().displayMetrics.density)

    return Pair(scaleWidth, scaleHeight)
}

fun blueToNearestRed(
    list: List<RedNode>,
    blueNode: BlueNode,
    obstacles: List<Obstacle>,
    redNode: RedNode = RedNode(999),
    redNode2: RedNode = RedNode(999)
): RedNode {
    val (x, y) = blueNode.location
    var closest = RedNode()
    var closestDistance = Float.MAX_VALUE
    for (i in list.indices) {
        val (x1, y1) = list[i].location
        val distance = (x - x1) * (x - x1) + (y - y1) * (y - y1)
        if (distance < closestDistance && checkIfSameLineHorizontalOrVertical(
                blueNode, list[i]
            ) && checkForObstacles(
                blueNode, list[i], obstacles
            ) && list[i] != redNode && list[i] != redNode2
        ) {
            closest = list[i]
            closestDistance = distance
        }
    }
    return closest
}

fun checkForObstacles(node1: Node, node2: Node, obstacles: List<Obstacle>): Boolean {
    val (x1, y1) = node1.location
    val (x2, y2) = node2.location

    for (obstacle in obstacles) {
        val (ox, oy) = obstacle.location
        val margin = 5

        // Define the bounding box of the obstacle
        val left = ox - margin
        val right = ox + margin
        val top = oy - margin
        val bottom = oy + margin

        // Check if the line segment intersects with the bounding box
        if (lineIntersectsRect(x1, y1, x2, y2, left, top, right, bottom)) {
            return false
        }
    }
    return true
}

fun lineIntersectsRect(x1: Float, y1: Float, x2: Float, y2: Float, left: Float, top: Float, right: Float, bottom: Float): Boolean {
    // Check if the line intersects any of the four edges of the rectangle
    return lineIntersectsLine(x1, y1, x2, y2, left, top, right, top) || // Top edge
            lineIntersectsLine(x1, y1, x2, y2, left, bottom, right, bottom) || // Bottom edge
            lineIntersectsLine(x1, y1, x2, y2, left, top, left, bottom) || // Left edge
            lineIntersectsLine(x1, y1, x2, y2, right, top, right, bottom) // Right edge
}

fun lineIntersectsLine(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float, x4: Float, y4: Float): Boolean {
    val denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1)
    if (denominator == 0f) {
        return false // Lines are parallel
    }
    val ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denominator
    val ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denominator
    return ua in 0.0..1.0 && ub in 0.0..1.0
}

fun <T : Node> findDistance(node1: T, node2: T): Float {
    val (x1, y1) = node1.location
    val (x2, y2) = node2.location
    return (abs(x1 - x2) * abs(x1 - x2) + abs(y1 - y2) * abs(y1 - y2))
}
