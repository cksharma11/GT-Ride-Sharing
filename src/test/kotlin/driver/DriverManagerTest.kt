package driver

import common.entity.Location
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DriverManagerTest {
    private lateinit var driverManager: DriverManager
    private lateinit var driverStore: DriverStore

    @BeforeEach
    fun setUp() {
        driverStore = DriverStore(mutableMapOf())
        driverManager = DriverManager(driverStore)
    }

    @Test
    fun testAddDriver() {
        val driverId = "123"
        val location = Location(1, 1)

        driverManager.addDriver(driverId, location)

        val drivers = driverManager.getAllDrivers()
        assertEquals(1, drivers.size)
        assertEquals(driverId, drivers[0].id)
        assertEquals(location, drivers[0].location)
    }

    @Test
    fun testGetAllDriversEmpty() {
        val drivers = driverManager.getAllDrivers()
        assertEquals(0, drivers.size)
    }

    @Test
    fun testGetAllDriversMultiple() {
        val driver1Id = "123"
        val driver1Location = Location(1, 1)
        val driver2Id = "456"
        val driver2Location = Location(2, 2)

        driverManager.addDriver(driver1Id, driver1Location)
        driverManager.addDriver(driver2Id, driver2Location)

        val drivers = driverManager.getAllDrivers()
        assertEquals(2, drivers.size)
        assertEquals(driver1Id, drivers[0].id)
        assertEquals(driver1Location, drivers[0].location)
        assertEquals(driver2Id, drivers[1].id)
        assertEquals(driver2Location, drivers[1].location)
    }
}