package driver

import common.entity.Location
import driver.boundary.DriverManagerBoundary
import driver.entity.Driver

class DriverManager(private val driverStore: DriverStore): DriverManagerBoundary {
    override fun addDriver(driverId: String, location: Location) {
        driverStore.addDriver(driverId, location)
    }

    override fun getAllDrivers(): List<Driver> {
        return driverStore.getAllDrivers()
    }
}