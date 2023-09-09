package driver

import common.entity.Location
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DriverStoreTest {
    private lateinit var driverStore: DriverStore

    @BeforeEach
    fun setUp() {
        driverStore = DriverStore(mutableMapOf())
    }

    @Test
    fun testAddDriver() {
        val driverId = "123"
        val location = Location(1, 1)

        driverStore.addDriver(driverId, location)

        val drivers = driverStore.getAllDrivers()
        assertEquals(1, drivers.size)
        assertEquals(driverId, drivers[0].id)
        assertEquals(location, drivers[0].location)
    }

    @Test
    fun testGetAllDriversEmpty() {
        val drivers = driverStore.getAllDrivers()
        assertTrue(drivers.isEmpty())
    }

    @Test
    fun testGetAllDriversMultiple() {
        val driver1Id = "123"
        val driver1Location = Location(1, 1)
        val driver2Id = "456"
        val driver2Location = Location(2, 2)

        driverStore.addDriver(driver1Id, driver1Location)
        driverStore.addDriver(driver2Id, driver2Location)

        val drivers = driverStore.getAllDrivers()
        assertEquals(2, drivers.size)
        assertEquals(driver1Id, drivers[0].id)
        assertEquals(driver1Location, drivers[0].location)
        assertEquals(driver2Id, drivers[1].id)
        assertEquals(driver2Location, drivers[1].location)
    }
}