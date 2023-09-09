package ride.boundary

interface RideMatchingStrategy {
    fun match(riderId: String) : List<String>
}