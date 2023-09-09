package ride.entity

import common.entity.Location

data class Ride (
    val id: String,
    val driverId: String,
    val riderId: String,
    val start: Location,
    var timeTaken: Int? = null,
    var destination: Location? = null,
    var state: RideState = RideState.STARTED
)