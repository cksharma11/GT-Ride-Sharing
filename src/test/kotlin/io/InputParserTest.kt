package io

import common.exception.InvalidCommandException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import parser.CommandType
import parser.InputParser
import java.io.File

class InputParserTest {
    @Test
    fun testParseInput() {
        val tempDir = createTempDir()
        val inputFilePath = File(tempDir, "sample_input.txt")

        inputFilePath.writeText(
            "ADD_DRIVER D1 1 1\n"
            +"ADD_DRIVER D2 4 5\n"
            +"ADD_DRIVER D3 2 2\n"
            +"ADD_RIDER R1 0 0\n"
            +"MATCH R1\n"
            +"START_RIDE RIDE-001 2 R1\n"
            +"STOP_RIDE RIDE-001 4 5 32\n"
            +"BILL RIDE-001"
        )

        val commands = InputParser.parseInput(inputFilePath.absolutePath)

        assertEquals(8, commands.size)
        assertEquals(CommandType.ADD_DRIVER, commands[0].command)
        assertEquals(CommandType.ADD_RIDER, commands[3].command)
        assertEquals(CommandType.MATCH, commands[4].command)
        assertEquals(CommandType.START_RIDE, commands[5].command)
        assertEquals(CommandType.STOP_RIDE, commands[6].command)
        assertEquals(CommandType.BILL, commands[7].command)

        tempDir.deleteRecursively()
    }

    @Test
    fun testParseInputInvalidCommand() {
        val tempDir = createTempDir()
        val inputFilePath = File(tempDir, "sample_input.txt")

        inputFilePath.writeText("INVALID_COMMAND")
        assertThrows<InvalidCommandException> {
            InputParser.parseInput(inputFilePath.absolutePath)
        }

        tempDir.deleteRecursively()
    }
}