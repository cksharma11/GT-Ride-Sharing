package logger

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ConsoleLoggerTest {
    @Test
    fun testLog() {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
        val originalOut = System.out
        System.setOut(printStream)

        val logger = ConsoleLogger()

        val message = "This is a test message"
        logger.log(message)

        System.setOut(originalOut)

        val printedMessage = outputStream.toString().trim()
        assertEquals(message, printedMessage)
    }
}