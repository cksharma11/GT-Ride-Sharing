package command

import command.executor.CommandExecutorBuilder
import logger.ConsoleLogger
import parser.Command
import parser.CommandType

class CommandProcessor(
    private val commands: List<Command>,
    private val commandExecutor: CommandExecutorBuilder,
    private val logger: ConsoleLogger
) {
    fun processCommands() {
        commands.forEach { command ->
            val output = commandExecutor.build(command.command).execute(command.args)
            if (output is String) {
                logger.log(output)
            }
        }
    }
}