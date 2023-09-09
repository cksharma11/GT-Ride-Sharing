package ride_sharing.boundary

import ride.entity.Bill

interface RideSharingManagerBoundary {
    fun addDriver(driverId: String, x: Int, y: Int)

    fun addRider(riderId: String, x: Int, y: Int)

    fun match(riderId: String): String

    fun startRide(rideId: String, driverId: String, riderId: String): String

    fun stopRide(rideId: String, destinationX: Int, destinationY: Int, timeTaken: Int): String

    fun bill(rideId: String): String
}