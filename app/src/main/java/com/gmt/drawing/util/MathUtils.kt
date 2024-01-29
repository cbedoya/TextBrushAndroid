package com.gmt.drawing.util

import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * distance between two points calculated with the formula:
 * d = √((x₂-x₁)²+(y₂-y₁)²)
 */
fun Offset.distanceTo(dst: Offset) = sqrt(
    (dst.x - this.x).pow(2) + (dst.y - this.y).pow(2)
)

/**
 * finds slope from two points with the formula
 * slope = (y₂ - y₁)/(x₂ - x₁)
 */
fun Offset.slope(dst: Offset) =
    if (dst.x - this.x == 0f)
        Float.POSITIVE_INFINITY
    else
        (dst.y - this.y) / (dst.x - this.x)

fun Offset.findPointOnLine(slope: Float, distance: Float, leftToRight: Boolean, topToBottom: Boolean): Offset {
    val distanceX = if (leftToRight) distance else -distance
    val distanceY = if (topToBottom) distance else -distance
    val x = this.x + distanceX / sqrt(1 + slope * slope)
    if (slope == Float.POSITIVE_INFINITY) {
        return Offset(x, this.y + distanceY)
    } else {
        val y = this.y + slope * (x - this.x)
        return Offset(x, y)
    }
}

/**
 * converts radians to degrees
 */
fun Float.toDegrees(): Float = (this * 180 / PI).toFloat()