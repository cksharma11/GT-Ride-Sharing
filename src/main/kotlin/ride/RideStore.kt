package ride

import common.entity.Location
import common.exception.RideCannotBeEndedException
import common.exception.RideAlreadyExistsException
import common.exception.RiderNotFoundException
import ride.boundary.RideStoreBoundary
import ride.entity.Ride
import ride.entity.RideState

class RideStore(private val rides: MutableMap<String, Ride>) : RideStoreBoundary {
    override fun addRide(ride: Ride) {
        if (rides[ride.id] != null) {
            throw RideAlreadyExistsException("Ride already exists with id ${ride.id}")
        }
        rides[ride.id] = ride
    }

    override fun stopRide(rideId: String, timeTaken: Int, destination: Location) {
        val ride = rides[rideId]
        if (ride != null && ride.state != RideState.ENDED) {
            ride.state = RideState.ENDED
            ride.timeTaken = timeTaken
            ride.destination = destination
        } else {
            throw throw RideCannotBeEndedException("Ride didn't exists or already ended.")
        }
    }

    override fun getRide(rideId: String): Ride? {
        return rides[rideId]
    }
}