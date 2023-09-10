package io

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import parser.CommandType
import parser.InputParser
import java.io.FileNotFoundException

class InputParserTest {
    @Test
    fun testParseInput() {
        val commands = InputParser.parseInput("test-input-1")

        assertEquals(8, commands.size)
        assertEquals(CommandType.ADD_DRIVER, commands[0].command)
        assertEquals(CommandType.ADD_RIDER, commands[3].command)
        assertEquals(CommandType.MATCH, commands[4].command)
        assertEquals(CommandType.START_RIDE, commands[5].command)
        assertEquals(CommandType.STOP_RIDE, commands[6].command)
        assertEquals(CommandType.BILL, commands[7].command)
    }

    @Test
    fun testParseInputInvalidCommand() {
        assertThrows<FileNotFoundException> { InputParser.parseInput("invalid-input-file") }
    }
}