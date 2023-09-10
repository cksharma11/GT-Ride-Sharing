package command.executor

import ride_sharing.RideSharingManager

class MatchCommandExecutor(
    rideSharingManager: RideSharingManager, matches: MutableList<String>
) : AbstractCommandExecutor(rideSharingManager, matches) {
    override fun execute(args: List<String>): String {
        val riderId = args[0]
        val match = rideSharingManager.match(riderId)
        matches.add(match)
        return match
    }
}