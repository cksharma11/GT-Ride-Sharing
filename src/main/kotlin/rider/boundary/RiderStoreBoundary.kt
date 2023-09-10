package rider.boundary

import common.entity.Location
import driver.entity.Driver
import rider.Rider

interface RiderStoreBoundary {
    fun addRider(riderId: String, location: Location)

    fun getRider(riderId: String): Rider?
}