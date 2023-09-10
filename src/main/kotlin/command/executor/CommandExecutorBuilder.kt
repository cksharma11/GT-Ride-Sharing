package command.executor

import parser.CommandType
import ride_sharing.RideSharingManager

class CommandExecutorBuilder(private val rideSharingManager: RideSharingManager) {
    fun build(commandName: CommandType): CommandExecutor {
        return when (commandName) {
            CommandType.ADD_DRIVER -> AddDriverCommandExecutor(rideSharingManager)
            CommandType.ADD_RIDER -> AddRiderCommandExecutor(rideSharingManager)
            CommandType.MATCH -> MatchCommandExecutor(rideSharingManager)
            CommandType.START_RIDE -> StartRideCommandExecutor(rideSharingManager)
            CommandType.STOP_RIDE -> StopRideCommandExecutor(rideSharingManager)
            CommandType.BILL -> BillCommandExecutor(rideSharingManager)
        }
    }
}