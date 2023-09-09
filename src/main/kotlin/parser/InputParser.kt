package parser

import common.exception.InvalidCommandException
import io.FileOperation

object InputParser {
    fun parseInput(inputFilePath: String): List<Command> {
        val inputString = FileOperation.readFileAsString(inputFilePath)
        val inputLines =  inputString.split("\n")
        return inputLines
            .filter { it.isNotEmpty() }
            .map { mapInput(it) }
    }

    private fun mapInput(input: String): Command {
        val split = input.split(" ")
        val command = split[0]
        val args = split.subList(1, split.size)

        return when(command) {
            CommandType.ADD_DRIVER.toString() -> Command(CommandType.ADD_DRIVER, args)
            CommandType.ADD_RIDER.toString() -> Command(CommandType.ADD_RIDER, args)
            CommandType.MATCH.toString() -> Command(CommandType.MATCH, args)
            CommandType.START_RIDE.toString() -> Command(CommandType.START_RIDE, args)
            CommandType.STOP_RIDE.toString() -> Command(CommandType.STOP_RIDE, args)
            CommandType.BILL.toString() -> Command(CommandType.BILL, args)
            else -> throw InvalidCommandException("Invalid command provided.")
        }
    }
}