package command_executor

import parser.CommandType
import ride_sharing.RideSharingManager

interface CommandExecutor {
    fun execute(args: List<String>): Any
}

abstract class AbstractCommandExecutor(val rideSharingManager: RideSharingManager) : CommandExecutor

class AddDriverCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>) {
        val driverId = args[0]
        val x = args[1].toInt()
        val y = args[2].toInt()
        return rideSharingManager.addDriver(driverId, x, y)
    }
}

class AddRiderCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>) {
        val riderId = args[0]
        val x = args[1].toInt()
        val y = args[2].toInt()
        return rideSharingManager.addRider(riderId, x, y)
    }
}

class MatchCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>): String {
        val riderId = args[0]
        return rideSharingManager.match(riderId)
    }
}

class StartRideCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        val driverId = args[1]
        val riderId = args[2]
        return rideSharingManager.startRide(rideId, driverId, riderId)
    }
}

class StopRideCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        val x = args[1].toInt()
        val y = args[2].toInt()
        val timeTaken = args[3].toInt()
        return rideSharingManager.stopRide(rideId, x, y, timeTaken)
    }
}

class BillCommandExecutor(rideSharingManager: RideSharingManager) : AbstractCommandExecutor(rideSharingManager) {
    override fun execute(args: List<String>): String {
        val rideId = args[0]
        return rideSharingManager.bill(rideId)
    }
}

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