package ride

import ride.boundary.RideMatchingStrategy

class RideMatcher(private val rideMatchingStrategy: RideMatchingStrategy) {
    fun match(riderId: String): List<String> {
        return rideMatchingStrategy.match(riderId)
    }
}