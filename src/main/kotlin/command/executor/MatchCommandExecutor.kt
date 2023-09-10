package command.executor

import command.HelperArgs
import ride_sharing.RideSharingManager

class MatchCommandExecutor(
    rideSharingManager: RideSharingManager, helperArgs: HelperArgs
) : AbstractCommandExecutor(rideSharingManager, helperArgs) {
    override fun execute(args: List<String>): String {
        val riderId = args[0]
        val match = rideSharingManager.match(riderId)
        helperArgs.addMatch(match)
        return match
    }
}