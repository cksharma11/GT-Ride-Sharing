package command.executor

import ride_sharing.RideSharingManager

class BillCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        return rideSharingManager.bill(rideId)
    }
}