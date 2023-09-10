package logger

import logger.boundary.Logger

class ConsoleLogger: Logger {
    override fun log(message: String) {
        println(message)
    }
}