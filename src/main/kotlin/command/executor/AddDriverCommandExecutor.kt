package command.executor

import ride_sharing.RideSharingManager

class AddDriverCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>) {
        val driverId = args[0]
        val x = args[1].toInt()
        val y = args[2].toInt()
        return rideSharingManager.addDriver(driverId, x, y)
    }
}