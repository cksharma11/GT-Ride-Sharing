package ride_sharing

import driver.boundary.DriverManagerBoundary
import common.entity.Location
import ride.boundary.RideManagerBoundary
import ride_sharing.boundary.RideSharingManagerBoundary
import rider.boundary.RiderManagerBoundary

class RideSharingManager(
    private val driverManager: DriverManagerBoundary,
    private val riderManager: RiderManagerBoundary,
    private val rideManager: RideManagerBoundary
): RideSharingManagerBoundary {
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
        if(drivers.isEmpty()) {
            return "NO_DRIVER_MATCHED"
        }
        var output = "DRIVERS_MATCHED"
        drivers.forEach { output += " $it" }
        return output
    }

    override fun startRide(rideId: String, driverId: String, riderId: String): String {
        val rider = riderManager.getRider(riderId)
        rideManager.startRide(rideId, driverId, riderId, rider.location)
        return "RIDE_STARTED $rideId"
    }

    override fun stopRide(rideId: String, destinationX: Int, destinationY: Int, timeTaken: Int): String {
        val destination = Location(destinationX, destinationY)
        rideManager.stopRide(rideId, timeTaken, destination)
        return "RIDE_STOPPED $rideId"
    }

    override fun bill(rideId: String): String {
        val bill = rideManager.bill(rideId)
        if(bill.error != null) {
            return bill.error
        }
        return "BILL ${bill.riderId} ${bill.riderId} ${bill.totalFare}"
    }
}