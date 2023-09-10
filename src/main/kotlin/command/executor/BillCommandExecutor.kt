package command.executor

import ride_sharing.RideSharingManager

class BillCommandExecutor(
    rideSharingManager: RideSharingManager, matches: MutableList<String>
) : AbstractCommandExecutor(rideSharingManager, matches) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        return rideSharingManager.bill(rideId)
    }
}