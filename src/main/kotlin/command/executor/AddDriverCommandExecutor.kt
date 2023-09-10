package command.executor

import command.HelperArgs
import ride_sharing.RideSharingManager

class AddDriverCommandExecutor(
    rideSharingManager: RideSharingManager, helperArgs: HelperArgs
) : AbstractCommandExecutor(rideSharingManager, helperArgs) {
    override fun execute(args: List<String>) {
        val driverId = args[0]
        val x = args[1].toInt()
        val y = args[2].toInt()
        return rideSharingManager.addDriver(driverId, x, y)
    }
}