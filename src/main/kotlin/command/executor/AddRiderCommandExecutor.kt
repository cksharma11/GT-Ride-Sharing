package command.executor

import command.HelperArgs
import ride_sharing.RideSharingManager

class AddRiderCommandExecutor(
    rideSharingManager: RideSharingManager, helperArgs: HelperArgs
) : AbstractCommandExecutor(rideSharingManager, helperArgs) {
    override fun execute(args: List<String>) {
        val riderId = args[0]
        val x = args[1].toInt()
        val y = args[2].toInt()
        return rideSharingManager.addRider(riderId, x, y)
    }
}