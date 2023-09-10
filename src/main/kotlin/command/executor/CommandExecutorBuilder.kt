package command.executor

import command.HelperArgs
import parser.CommandType
import ride_sharing.RideSharingManager

class CommandExecutorBuilder(
    private val rideSharingManager: RideSharingManager,
    private val matches: HelperArgs
) {
    fun build(commandName: CommandType): CommandExecutor {
        return when (commandName) {
            CommandType.ADD_DRIVER -> AddDriverCommandExecutor(rideSharingManager, matches)
            CommandType.ADD_RIDER -> AddRiderCommandExecutor(rideSharingManager, matches)
            CommandType.MATCH -> MatchCommandExecutor(rideSharingManager, matches)
            CommandType.START_RIDE -> StartRideCommandExecutor(rideSharingManager, matches)
            CommandType.STOP_RIDE -> StopRideCommandExecutor(rideSharingManager, matches)
            CommandType.BILL -> BillCommandExecutor(rideSharingManager, matches)
        }
    }
}