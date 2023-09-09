package driver

import driver.entity.Driver
import common.entity.Location

class DriverStore(private val drivers: MutableMap<String, Driver>) {
    fun addDriver(driverId: String, location: Location) {
        drivers[driverId] = Driver(driverId, location)
    }

    fun getAllDrivers(): List<Driver> {
        return drivers.values.toList()
    }
}