package command

import command.executor.CommandExecutorBuilder
import logger.boundary.Logger
import parser.Command

class CommandProcessor(
    private val commands: List<Command>,
    private val commandExecutor: CommandExecutorBuilder,
    private val logger: Logger
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