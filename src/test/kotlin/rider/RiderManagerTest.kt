package rider

import common.entity.Location
import common.exception.RiderNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class RiderManagerTest {
    private lateinit var riderManager: RiderManager
    private lateinit var riderStore: RiderStore

    @BeforeEach
    fun setUp() {
        riderStore = RiderStore(mutableMapOf())
        riderManager = RiderManager(riderStore)
    }

    @Test
    fun testAddRider() {
        val riderId = "123"
        val location = Location(1, 1)

        riderManager.addRider(riderId, location)

        val retrievedRider = riderManager.getRider(riderId)
        assertEquals(riderId, retrievedRider.id)
        assertEquals(location, retrievedRider.location)
    }

    @Test
    fun testGetRiderLocation() {
        val riderId = "123"
        val location = Location(1, 1)
        riderManager.addRider(riderId, location)

        val retrievedLocation = riderManager.getRiderLocation(riderId)
        assertEquals(location, retrievedLocation)
    }

    @Test
    fun testGetRiderLocationNotFound() {
        val riderId = "456"

        assertFailsWith<RiderNotFoundException> {
            riderManager.getRiderLocation(riderId)
        }
    }

    @Test
    fun testGetRider() {
        val riderId = "123"
        val location = Location(1, 1)
        riderManager.addRider(riderId, location)

        val retrievedRider = riderManager.getRider(riderId)
        assertEquals(riderId, retrievedRider.id)
        assertEquals(location, retrievedRider.location)
    }

    @Test
    fun testGetRiderNotFound() {
        val riderId = "456"

        assertFailsWith<RiderNotFoundException> {
            riderManager.getRider(riderId)
        }
    }
}