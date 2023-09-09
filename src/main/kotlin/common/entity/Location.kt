package common.entity

import extension.roundToTwoDecimalPlaces

data class Location (
    val x: Int,
    val y: Int,
)

fun Location.findDistanceFrom(l: Location): Double {
    val deltaX = x - l.x
    val deltaY = y - l.y
    return kotlin.math.sqrt((deltaX * deltaX + deltaY * deltaY).toDouble()).roundToTwoDecimalPlaces()
}