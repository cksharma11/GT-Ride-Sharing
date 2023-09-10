package command.executor

import command.HelperArgs
import ride_sharing.RideSharingManager

class BillCommandExecutor(
    rideSharingManager: RideSharingManager, helperArgs: HelperArgs
) : AbstractCommandExecutor(rideSharingManager, helperArgs) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        return rideSharingManager.bill(rideId)
    }
}