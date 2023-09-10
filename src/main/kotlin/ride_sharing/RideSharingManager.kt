package ride_sharing

import driver.boundary.DriverManagerBoundary
import common.entity.Location
import common.exception.DriverAlreadyOnRideException
import common.exception.DriverNotFoundException
import common.exception.RideCannotBeEndedException
import messages.Messages.BILL_MESSAGE
import messages.Messages.DRIVERS_MATCHED_MESSAGE
import messages.Messages.INVALID_RIDE_MESSAGE
import messages.Messages.NO_DRIVERS_AVAILABLE_MESSAGE
import messages.Messages.RIDE_STARTED_MESSAGE
import messages.Messages.RIDE_STOPPED_MESSAGE
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
            return NO_DRIVERS_AVAILABLE_MESSAGE
        }
        var output = DRIVERS_MATCHED_MESSAGE
        drivers.forEach { output += " $it" }
        return output
    }

    override fun startRide(rideId: String, driverId: String, riderId: String): String {
        val rider = riderManager.getRider(riderId)
        return try {
            rideManager.startRide(rideId, driverId, riderId, rider.location)
            "$RIDE_STARTED_MESSAGE $rideId"
        } catch (ex: DriverAlreadyOnRideException) {
            INVALID_RIDE_MESSAGE
        } catch (ex: DriverNotFoundException) {
            INVALID_RIDE_MESSAGE
        }
    }

    override fun stopRide(rideId: String, destinationX: Int, destinationY: Int, timeTaken: Int): String {
        val destination = Location(destinationX, destinationY)
        return try {
            rideManager.stopRide(rideId, timeTaken, destination)
            "$RIDE_STOPPED_MESSAGE $rideId"
        } catch (ex: RideCannotBeEndedException) {
            INVALID_RIDE_MESSAGE
        }
    }

    override fun bill(rideId: String): String {
        val bill = rideManager.bill(rideId)
        if (bill.error != null) {
            return bill.error
        }
        return "$BILL_MESSAGE ${bill.riderId} ${bill.riderId} ${bill.totalFare}"
    }
}