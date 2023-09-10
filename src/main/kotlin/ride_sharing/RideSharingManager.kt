package ride_sharing

import driver.boundary.DriverManagerBoundary
import common.entity.Location
import common.exception.DriverAlreadyOnRideException
import common.exception.DriverNotFoundException
import common.exception.RideCannotBeEndedException
import ride.boundary.RideManagerBoundary
import ride_sharing.boundary.RideSharingManagerBoundary
import rider.boundary.RiderManagerBoundary

class RideSharingManager(
    private val driverManager: DriverManagerBoundary,
    private val riderManager: RiderManagerBoundary,
    private val rideManager: RideManagerBoundary
) : RideSharingManagerBoundary {
    override fun addDriver(driverId: String, x: Int, y: Int) {
        val driverLocation = Location(x, y)
        driverManager.addDriver(driverId, driverLocation)
    }

    override fun addRider(riderId: String, x: Int, y: Int) {
        val riderLocation = Location(x, y)
        riderManager.addRider(riderId, riderLocation)
    }

    override fun match(riderId: String): String {
        val drivers = rideManager.match(riderId)
        if (drivers.isEmpty()) {
            return "NO_DRIVERS_AVAILABLE"
        }
        var output = "DRIVERS_MATCHED"
        drivers.forEach { output += " $it" }
        return output
    }

    override fun startRide(rideId: String, driverId: String, riderId: String): String {
        val rider = riderManager.getRider(riderId)
        return try {
            rideManager.startRide(rideId, driverId, riderId, rider.location)
            "RIDE_STARTED $rideId"
        } catch (ex: DriverAlreadyOnRideException) {
            "INVALID_RIDE"
        } catch (ex: DriverNotFoundException) {
            "INVALID_RIDE"
        }
    }

    override fun stopRide(rideId: String, destinationX: Int, destinationY: Int, timeTaken: Int): String {
        val destination = Location(destinationX, destinationY)
        return try {
            rideManager.stopRide(rideId, timeTaken, destination)
            "RIDE_STOPPED $rideId"
        } catch (ex: RideCannotBeEndedException) {
            "INVALID_RIDE"
        }
    }

    override fun bill(rideId: String): String {
        val bill = rideManager.bill(rideId)
        if (bill.error != null) {
            return bill.error
        }
        return "BILL ${bill.riderId} ${bill.riderId} ${bill.totalFare}"
    }
}