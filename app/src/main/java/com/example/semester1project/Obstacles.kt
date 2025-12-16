package com.example.semester1project

data class Obstacle (
    override var id: Int = 0,
    override var location: Pair<Float, Float> = Pair(0f, 0f),
) : Node