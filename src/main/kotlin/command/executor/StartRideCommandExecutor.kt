package command.executor

import ride_sharing.RideSharingManager

class StartRideCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        val driverId = args[1]
        val riderId = args[2]
        return rideSharingManager.startRide(rideId, driverId, riderId)
    }
}