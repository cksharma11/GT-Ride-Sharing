package ride.entity

data class Bill (
    val riderId: String? = null,
    val driverId: String? = null,
    val totalFare: Double? = null,
    val error: String? = null
)