package driver

import driver.entity.Driver
import common.entity.Location
import common.exception.DriverNotFoundException
import driver.boundary.DriverStoreBoundary

class DriverStore(private val drivers: MutableMap<String, Driver>): DriverStoreBoundary {
    override fun addDriver(driverId: String, location: Location) {
        drivers[driverId] = Driver(driverId, location)
    }

    override fun getAllDrivers(): List<Driver> {
        return drivers.values.toList()
    }

    override fun getDriver(driverId: String): Driver {
        return drivers[driverId] ?: throw DriverNotFoundException("Driver not found with id $driverId")
    }

    override fun setOnRideStatus(driverId: String, isOnRide: Boolean) {
        drivers[driverId]?.isOnRide = true
    }
}