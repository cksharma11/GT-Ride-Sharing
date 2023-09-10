package driver.boundary

import common.entity.Location
import driver.entity.Driver

interface DriverStoreBoundary {
    fun addDriver(driverId: String, location: Location)

    fun getAllDrivers(): List<Driver>
    fun getDriver(driverId: String): Driver
    fun setOnRideStatus(driverId: String, b: Boolean)
}