package ride.strategy

import common.entity.Location
import common.entity.findDistanceFrom
import driver.DriverManager
import ride.boundary.RideMatchingStrategy
import rider.RiderManager

class DriverWithin5KmsMatchStrategy(
    private val riderManager: RiderManager,
    private val driverManager: DriverManager
): RideMatchingStrategy {
    override fun match(riderId: String): List<String> {
        val riderLocation = riderManager.getRiderLocation(riderId)
        val drivers = driverManager.getAllDrivers()
        val driversInRange = drivers.filter { isWithinRange(riderLocation, it.location) }
        return driversInRange.map { it.id }
    }

    private fun isWithinRange(riderLocation: Location, driverLocation: Location): Boolean {
        val distance = riderLocation.findDistanceFrom(driverLocation)
        return distance <= 5
    }
}