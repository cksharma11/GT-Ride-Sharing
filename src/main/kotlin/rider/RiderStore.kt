package rider

import common.entity.Location
import rider.boundary.RiderStoreBoundary

class RiderStore(private val riders: MutableMap<String, Rider>): RiderStoreBoundary {
    override fun addRider(riderId: String, location: Location) {
        riders[riderId] = Rider(riderId, location)
    }

    override fun getRider(riderId: String): Rider? {
        return riders[riderId]
    }
}