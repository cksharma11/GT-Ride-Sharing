package ride

import common.entity.Location
import common.entity.findDistanceFrom
import common.exception.DriverAlreadyOnRideException
import driver.boundary.DriverStoreBoundary
import extension.roundToTwoDecimalPlaces
import ride.Charges.AdditionPerKm
import ride.Charges.AdditionPerMinute
import ride.Charges.BaseFare
import ride.Charges.ServiceTaxPercentage
import ride.boundary.RideManagerBoundary
import ride.boundary.RideMatchingStrategy
import ride.boundary.RideStoreBoundary
import ride.entity.Bill
import ride.entity.Ride
import ride.entity.RideState

class RideManager(
    private val rideMatchingStrategy: RideMatchingStrategy,
    private val rideStore: RideStoreBoundary,
    private val driverStore: DriverStoreBoundary
) : RideManagerBoundary {
    override fun match(riderId: String): List<String> {
        return rideMatchingStrategy.match(riderId)
    }

    override fun startRide(rideId: String, driverId: String, riderId: String, startLocation: Location) {
        val ride = Ride(rideId, driverId, riderId, startLocation)
        val driver = driverStore.getDriver(driverId)
        if (driver.isOnRide) {
            throw DriverAlreadyOnRideException("Driver $driverId already on ride")
        }
        rideStore.addRide(ride)
        driverStore.setOnRideStatus(driverId, true)
    }

    override fun stopRide(rideId: String, timeTaken: Int, destination: Location) {
        rideStore.stopRide(rideId, timeTaken, destination)
        val ride = rideStore.getRide(rideId)
        driverStore.setOnRideStatus(ride!!.driverId, false)
    }

    override fun bill(rideId: String): Bill {
        val ride = rideStore.getRide(rideId) ?: return Bill(error = "INVALID_RIDE")
        if (ride.state != RideState.ENDED) return Bill(error = "RIDE_NOT_COMPLETED")
        return Bill(ride.riderId, ride.driverId, totalFare = calculateFare(ride))
    }

    private fun calculateFare(ride: Ride): Double {
        val kmDistanceCovered = ride.start.findDistanceFrom(ride.destination!!)

        val additionalKmCharges = AdditionPerKm * kmDistanceCovered
        val additionalMinuteCharges = AdditionPerMinute * ride.timeTaken!!
        val fare = BaseFare.plus(additionalKmCharges).plus(additionalMinuteCharges)
        val serviceTax = fare * ServiceTaxPercentage.div(100.0)

        return fare.plus(serviceTax).roundToTwoDecimalPlaces()
    }
}