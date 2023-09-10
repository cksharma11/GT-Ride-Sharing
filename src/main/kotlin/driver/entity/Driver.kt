package driver.entity

import common.entity.Location

data class Driver (
    val id: String,
    val location: Location,
    var isOnRide: Boolean = false,
)