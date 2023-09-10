package ride

import common.entity.Location
import common.exception.DriverAlreadyOnRideException
import driver.DriverStore
import driver.entity.Driver
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

import ride.boundary.RideMatchingStrategy
import ride.entity.Ride
import ride.entity.RideState

class RideManagerTest {
    private lateinit var rideMatchingStrategy: RideMatchingStrategy
    private lateinit var rideStore: RideStore
    private lateinit var driverStore: DriverStore
    private lateinit var rideManager: RideManager

    @BeforeEach
    fun setUp() {
        rideMatchingStrategy = mock(RideMatchingStrategy::class.java)
        rideStore = mock(RideStore::class.java)
        driverStore = mock(DriverStore::class.java)
        rideManager = RideManager(rideMatchingStrategy, rideStore, driverStore)
    }

    @Test
    fun testMatch() {
        val riderId = "rider123"
        val matchedRides = listOf("ride1", "ride2")
        `when`(rideMatchingStrategy.match(riderId)).thenReturn(matchedRides)
        val result = rideManager.match(riderId)
        assertEquals(matchedRides, result)
    }

    @Test
    fun testStartRide() {
        val rideId = "ride123"
        val driverId = "driver456"
        val riderId = "rider789"
        val startLocation = Location(0, 0)
        val ride = Ride(rideId, driverId, riderId, startLocation)

        `when`(driverStore.getDriver(driverId)).thenReturn(Driver(driverId, Location(0, 0)))

        rideManager.startRide(rideId, driverId, riderId, startLocation)
        verify(rideStore).addRide(ride)
    }

    @Test
    fun testStartRideWhenDriverAlreadyOnRide() {
        val rideId = "ride123"
        val driverId = "driver456"
        val riderId = "rider789"
        val startLocation = Location(0, 0)

        `when`(driverStore.getDriver(driverId)).thenReturn(Driver(driverId, Location(0, 0), isOnRide = true))
        assertThrows<DriverAlreadyOnRideException> { rideManager.startRide(rideId, driverId, riderId, startLocation) }
    }

    @Test
    fun testStopRide() {
        val rideId = "ride123"
        val destination = Location(1, 1)
        val timeTaken = 10
        `when`(rideStore.getRide(rideId)).thenReturn(
            Ride(
                rideId, "driverId", "riderId", Location(0, 0), state = RideState.STARTED
            )
        )
        rideManager.stopRide(rideId, timeTaken, destination)
        verify(rideStore).stopRide(rideId, timeTaken, destination)
    }

    @Test
    fun testBillValidRide() {
        val rideId = "ride123"
        val ride = Ride(
            rideId, "driver456", "rider789", Location(0, 0), 32, Location(4, 5), RideState.ENDED
        )

        `when`(rideStore.getRide(rideId)).thenReturn(ride)
        val result = rideManager.bill(rideId)

        assertNotNull(result)
        assertEquals(ride.riderId, result.riderId)
        assertEquals(ride.driverId, result.driverId)
        assertEquals(186.72, result.totalFare)
    }

    @Test
    fun testBillForNotEndedRide() {
        val rideId = "ride123"
        val ride = Ride(
            rideId, "driver456", "rider789", Location(0, 0), 32, Location(4, 5), RideState.STARTED
        )

        `when`(rideStore.getRide(rideId)).thenReturn(ride)
        val result = rideManager.bill(rideId)

        assertNotNull(result)
        assertEquals("RIDE_NOT_COMPLETED", result.error)
        assertEquals(null, result.totalFare)
    }

    @Test
    fun testBillInvalidRide() {
        val rideId = "invalidRideId"
        `when`(rideStore.getRide(rideId)).thenReturn(null)
        val result = rideManager.bill(rideId)
        assertNotNull(result)
        assertEquals("INVALID_RIDE", result.error)
    }
}