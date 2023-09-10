package ride_sharing

import common.entity.Location
import driver.boundary.DriverManagerBoundary
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import ride.boundary.RideManagerBoundary
import ride.entity.Bill
import rider.Rider
import rider.boundary.RiderManagerBoundary

class RideSharingManagerTest {
    private lateinit var rideSharingManager: RideSharingManager
    private lateinit var driverManager: DriverManagerBoundary
    private lateinit var riderManager: RiderManagerBoundary
    private lateinit var rideManager: RideManagerBoundary

    @BeforeEach
    fun setUp() {
        driverManager = mock(DriverManagerBoundary::class.java)
        riderManager = mock(RiderManagerBoundary::class.java)
        rideManager = mock(RideManagerBoundary::class.java)
        rideSharingManager = RideSharingManager(driverManager, riderManager, rideManager)
    }

//    @Test
//    fun testAddDriver() {
//        val driverId = "D123"
//        val location = Location(1, 1)
//
//        `when`(driverManager.addDriver(driverId, location)).thenReturn(Unit)
//
//        rideSharingManager.addDriver(driverId, location.x, location.y)
//
//        verify(driverManager).addDriver(driverId, location)
//    }

//    @Test
//    fun testAddRider() {
//        val riderId = "R123"
//        val location = Location(2, 2)
//
//        doNothing().`when`(riderManager.addRider(riderId, location))
//
//        rideSharingManager.addRider(riderId, location.x, location.y)
//
//        verify(riderManager).addRider(riderId, location)
//    }

    @Test
    fun testMatch() {
        val riderId = "R123"
        val driverId1 = "D1"
        val driverId2 = "D2"
        val drivers = listOf(driverId1, driverId2)

        `when`(rideManager.match(riderId)).thenReturn(drivers)

        val result = rideSharingManager.match(riderId)
        assertEquals("DRIVERS_MATCHED $driverId1 $driverId2", result)
    }

    @Test
    fun testStartRide() {
        val rideId = "R123"
        val driverId = "D123"
        val riderId = "R456"
        val riderLocation = Location(3, 3)

        `when`(riderManager.getRider(riderId)).thenReturn(Rider(riderId, riderLocation))
        doNothing().`when`(rideManager).startRide(rideId, driverId, riderId, riderLocation)

        val result = rideSharingManager.startRide(rideId, driverId, riderId)
        assertEquals("RIDE_STARTED $rideId", result)
    }

    @Test
    fun testStopRide() {
        val rideId = "R123"
        val destinationX = 4
        val destinationY = 4

        doNothing().`when`(rideManager).stopRide(rideId, 10, Location(destinationX, destinationY))

        val result = rideSharingManager.stopRide(rideId, destinationX, destinationY, 10)
        assertEquals("RIDE_STOPPED $rideId", result)
    }

    @Test
    fun testBill() {
        val rideId = "R123"
        val riderId = "R456"
        val driverId = "driverId"
        val totalFare = 25.0

        val bill = Bill(riderId, driverId, totalFare)
        `when`(rideManager.bill(rideId)).thenReturn(bill)

        val result = rideSharingManager.bill(rideId)
        assertEquals("BILL $riderId $riderId $totalFare", result)
    }
}