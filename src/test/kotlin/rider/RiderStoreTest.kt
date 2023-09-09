package rider

import common.entity.Location
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RiderStoreTest {
    private lateinit var riderStore: RiderStore

    @BeforeEach
    fun setUp() {
        riderStore = RiderStore(mutableMapOf())
    }

    @Test
    fun testAddRider() {
        val riderId = "123"
        val location = Location(1, 1)

        riderStore.addRider(riderId, location)

        val retrievedRider = riderStore.getRider("123")
        assertEquals(riderId, retrievedRider?.id)
        assertEquals(location, retrievedRider?.location)
    }

    @Test
    fun testAddRiderDuplicate() {
        val riderId = "123"
        val location1 = Location(1, 1)
        val location2 = Location(2, 2)

        riderStore.addRider(riderId, location1)

        riderStore.addRider(riderId, location2)

        val retrievedRider = riderStore.getRider("123")
        assertEquals(riderId, retrievedRider?.id)
        assertEquals(location2, retrievedRider?.location) // The latest location should be stored
    }

    @Test
    fun testGetRider() {
        val riderId = "123"
        val location = Location(1, 1)

        riderStore.addRider(riderId, location)

        val retrievedRider = riderStore.getRider("123")
        assertEquals(riderId, retrievedRider?.id)
        assertEquals(location, retrievedRider?.location)
    }

    @Test
    fun testGetRiderNotFound() {
        val retrievedRider = riderStore.getRider("456")
        assertNull(retrievedRider)
    }
}
