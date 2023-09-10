package command.executor

import ride_sharing.RideSharingManager

class MatchCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>): String {
        val riderId = args[0]
        return rideSharingManager.match(riderId)
    }
}