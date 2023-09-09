package ride.boundary

import common.entity.Location
import ride.entity.Bill

interface RideManagerBoundary  {
    fun match(riderId: String): List<String>

    fun startRide(rideId: String, driverId: String, riderId: String, startLocation: Location)

    fun stopRide(rideId: String, timeTaken: Int, destination: Location)

    fun bill(rideId: String): Bill
}