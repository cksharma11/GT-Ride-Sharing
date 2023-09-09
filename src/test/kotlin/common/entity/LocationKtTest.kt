package common.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LocationKtTest {
    @Test
    fun testFindDistanceFrom_SameLocation() {
        val location1 = Location(1, 1)
        val location2 = Location(1, 1)
        val distance = location1.findDistanceFrom(location2)
        assertEquals(0.0, distance, 0.01)
    }

    @Test
    fun testFindDistanceFrom_HorizontalDistance() {
        val location1 = Location(1, 1)
        val location2 = Location(4, 1)
        val distance = location1.findDistanceFrom(location2)
        assertEquals(3.0, distance, 0.01)
    }

    @Test
    fun testFindDistanceFrom_VerticalDistance() {
        val location1 = Location(1, 1)
        val location2 = Location(1, 4)
        val distance = location1.findDistanceFrom(location2)
        assertEquals(3.0, distance, 0.01)
    }

    @Test
    fun testFindDistanceFrom_DiagonalDistance() {
        val location1 = Location(1, 1)
        val location2 = Location(4, 4)
        val distance = location1.findDistanceFrom(location2)
        assertEquals(4.24, distance, 0.01)
    }

    @Test
    fun testFindDistanceFrom_RoundingToTwoDecimalPlaces() {
        val location1 = Location(1, 1)
        val location2 = Location(3, 4)
        val distance = location1.findDistanceFrom(location2)
        assertEquals(3.61, distance, 0.01)
    }
}