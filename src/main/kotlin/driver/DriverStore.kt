package driver

import driver.entity.Driver
import common.entity.Location
import driver.boundary.DriverStoreBoundary

class DriverStore(private val drivers: MutableMap<String, Driver>): DriverStoreBoundary {
    override fun addDriver(driverId: String, location: Location) {
        drivers[driverId] = Driver(driverId, location)
    }

    override fun getAllDrivers(): List<Driver> {
        return drivers.values.toList()
    }
}