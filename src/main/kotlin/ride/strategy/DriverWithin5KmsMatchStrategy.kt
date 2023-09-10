package ride.strategy

import common.entity.findDistanceFrom
import driver.DriverManager
import driver.entity.Driver
import ride.boundary.RideMatchingStrategy
import rider.RiderManager

class DriverWithin5KmsMatchStrategy(
    private val riderManager: RiderManager, private val driverManager: DriverManager
) : RideMatchingStrategy {
    override fun match(riderId: String): List<String> {
        val riderLocation = riderManager.getRiderLocation(riderId)
        val drivers = driverManager.getAllDrivers()

        val driverDistances = drivers.map { driver: Driver ->
            Pair(driver.id, riderLocation.findDistanceFrom(driver.location))
        }

        return driverDistances.filter { isWithinRange(it.second) }.sortedBy { it.second }.map { it.first }
    }

    private fun isWithinRange(distance: Double): Boolean {
        return distance <= 5
    }
}