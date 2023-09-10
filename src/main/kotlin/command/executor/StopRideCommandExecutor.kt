package command.executor

import command.HelperArgs
import ride_sharing.RideSharingManager

class StopRideCommandExecutor(
    rideSharingManager: RideSharingManager, helperArgs: HelperArgs
) : AbstractCommandExecutor(rideSharingManager, helperArgs) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        val x = args[1].toInt()
        val y = args[2].toInt()
        val timeTaken = args[3].toInt()
        return rideSharingManager.stopRide(rideId, x, y, timeTaken)
    }
}