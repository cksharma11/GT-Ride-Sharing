package command.executor

import ride_sharing.RideSharingManager

class AddRiderCommandExecutor(
    rideSharingManager: RideSharingManager, matches: MutableList<String>
) : AbstractCommandExecutor(rideSharingManager, matches) {
    override fun execute(args: List<String>) {
        val riderId = args[0]
        val x = args[1].toInt()
        val y = args[2].toInt()
        return rideSharingManager.addRider(riderId, x, y)
    }
}