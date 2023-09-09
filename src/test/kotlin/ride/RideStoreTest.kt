package ride

import common.entity.Location
import common.exception.RideAlreadyExistsException
import common.exception.RideCannotBeEndedException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ride.entity.Ride
import ride.entity.RideState
import kotlin.test.assertFailsWith

class RideStoreTest {
    private lateinit var rideStore: RideStore

    @BeforeEach
    fun setUp() {
        rideStore = RideStore(mutableMapOf())
    }

    @Test
    fun testAddRide() {
        val ride = Ride("123", "driverId", "riderId", Location(1, 1))
        rideStore.addRide(ride)

        val retrievedRide = rideStore.getRide("123")
        assertEquals(ride, retrievedRide)
    }

    @Test
    fun testAddRideAlreadyExists() {
        val ride = Ride("123", "driverId", "riderId", Location(1, 1))
        rideStore.addRide(ride)

        val exception = assertFailsWith<RideAlreadyExistsException> {
            rideStore.addRide(ride)
        }
        assertEquals("Ride already exists with id 123", exception.message)
    }

    @Test
    fun testStopRide() {
        val ride = Ride("123", "driverId", "riderId", Location(1, 1))
        rideStore.addRide(ride)

        rideStore.stopRide("123", 10, Location(2, 2))

        val retrievedRide = rideStore.getRide("123")
        assertEquals(RideState.ENDED, retrievedRide?.state)
        assertEquals(10, retrievedRide?.timeTaken)
        assertEquals(Location(2, 2), retrievedRide?.destination)
    }

    @Test
    fun testStopRideNotFound() {
        val exception = assertFailsWith<RideCannotBeEndedException> {
            rideStore.stopRide("123", 10, Location(2, 2))
        }
        assertEquals("Ride didn't exists or already ended.", exception.message)
    }

    @Test
    fun testGetRide() {
        val ride = Ride("123", "driverId", "riderId", Location(1, 1))
        rideStore.addRide(ride)

        val retrievedRide = rideStore.getRide("123")
        assertEquals(ride, retrievedRide)
    }

    @Test
    fun testGetRideNotFound() {
        val retrievedRide = rideStore.getRide("456")
        assertNull(retrievedRide)
    }
}