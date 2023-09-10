package command.executor

import command.HelperArgs
import ride_sharing.RideSharingManager

class StartRideCommandExecutor(
    rideSharingManager: RideSharingManager, helperArgs: HelperArgs
) : AbstractCommandExecutor(rideSharingManager, helperArgs) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        val driverNo = args[1]
        val riderId = args[2]
        val driverId = helperArgs.getMatch().split(" ")[driverNo.toInt()]
        helperArgs.removeMatch()

        return rideSharingManager.startRide(rideId, driverId, riderId)
    }
}