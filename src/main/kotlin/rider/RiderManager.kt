package rider

import common.entity.Location
import common.exception.RiderNotFoundException
import rider.boundary.RiderManagerBoundary
import rider.boundary.RiderStoreBoundary

class RiderManager(private val riderStore: RiderStoreBoundary): RiderManagerBoundary {
    override fun addRider(riderId: String, location: Location) {
        riderStore.addRider(riderId, location)
    }

    override fun getRiderLocation(riderId: String): Location {
        val rider = riderStore.getRider(riderId) ?: throw RiderNotFoundException("rider not found with id $riderId")
        return rider.location;
    }

    override fun getRider(riderId: String): Rider {
        return riderStore.getRider(riderId) ?: throw RiderNotFoundException("rider not found with id $riderId")
    }
}