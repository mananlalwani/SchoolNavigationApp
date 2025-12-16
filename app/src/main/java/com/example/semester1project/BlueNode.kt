package com.example.semester1project

data class BlueNode(
    override var id: Int = 0,
    override var location: Pair<Float, Float> = Pair(0f, 0f),
    var redNodes: List<RedNode> = emptyList(),
    var rooms: List<Int> = emptyList(),
) : Node
