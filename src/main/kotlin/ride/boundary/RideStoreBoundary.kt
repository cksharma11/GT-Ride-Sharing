package ride.boundary

import common.entity.Location
import driver.entity.Driver
import ride.entity.Ride

interface RideStoreBoundary {
    fun addRide(ride: Ride)

    fun stopRide(rideId: String, timeTaken: Int, destination: Location)

    fun getRide(rideId: String): Ride?
}