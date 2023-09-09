package extension

import kotlin.math.roundToLong

fun Double.roundToTwoDecimalPlaces(): Double {
    return (this * 100).roundToLong() / 100.0
}