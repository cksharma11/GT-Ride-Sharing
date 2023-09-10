package command.executor

interface CommandExecutor {
    fun execute(args: List<String>): Any
}
