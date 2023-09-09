package rider.boundary

import common.entity.Location
import rider.Rider

interface RiderManagerBoundary {
    fun addRider(riderId: String, location: Location)
    fun getRiderLocation(riderId: String): Location
    fun getRider(riderId: String): Rider
}