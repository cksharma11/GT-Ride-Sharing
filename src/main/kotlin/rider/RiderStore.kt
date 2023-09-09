package rider

import common.entity.Location

class RiderStore(private val riders: MutableMap<String, Rider>) {
    fun addRider(riderId: String, location: Location) {
        riders[riderId] = Rider(riderId, location)
    }

    fun getRider(riderId: String): Rider? {
        return riders[riderId]
    }
}