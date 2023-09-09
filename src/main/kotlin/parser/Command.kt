package parser

data class Command (
    val command: CommandType,
    val args: List<String>
)