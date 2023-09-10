package command.executor

import ride_sharing.RideSharingManager

class StartRideCommandExecutor(
    rideSharingManager: RideSharingManager, matches: MutableList<String>
) : AbstractCommandExecutor(rideSharingManager, matches) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        val driverNo = args[1]
        val riderId = args[2]
        val driverId = matches[0].split(" ")[driverNo.toInt()]
        matches.removeAt(0)

        return rideSharingManager.startRide(rideId, driverId, riderId)
    }
}