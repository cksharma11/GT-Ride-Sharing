package command

import command.executor.CommandExecutorBuilder
import driver.DriverManager
import driver.DriverStore
import driver.entity.Driver
import logger.ConsoleLogger
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test
import parser.InputParser
import ride.RideManager
import ride.RideStore
import ride.entity.Ride
import ride.strategy.DriverWithin5KmsMatchStrategy
import ride_sharing.RideSharingManager
import rider.Rider
import rider.RiderManager
import rider.RiderStore
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class CommandProcessorTest {
    private lateinit var commandProcessor: CommandProcessor

    @Test
    fun testWithTestFile1WithConsoleLogger() {
        setUpForInput("test-input-1")
        val expectedOutput =
            "DRIVERS_MATCHED D1 D3\n" + "RIDE_STARTED RIDE-001\n" + "RIDE_STOPPED RIDE-001\n" + "BILL R1 R1 186.72"

        assertCommandProcessorOutput(expectedOutput)
    }

    @Test
    fun testWithTestFile2WithConsoleLogger() {
        setUpForInput("test-input-2")
        val expectedOutput =
            "DRIVERS_MATCHED D2 D3 D1\n" + "DRIVERS_MATCHED D1 D2 D3\n" +
                    "RIDE_STARTED RIDE-101\n" + "RIDE_STARTED RIDE-102\n" +
                    "RIDE_STOPPED RIDE-101\n" + "RIDE_STOPPED RIDE-102\n" +
                    "BILL R1 R1 234.64\n" + "BILL R2 R2 258.0"

        assertCommandProcessorOutput(expectedOutput)
    }

    private fun assertCommandProcessorOutput(expectedOutput: String) {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
        val originalOut = System.out
        System.setOut(printStream)

        val executor = Executors.newSingleThreadExecutor()

        try {
            val future = executor.submit {
                commandProcessor.processCommands()
            }
            future.get(5000000, TimeUnit.SECONDS)
        } catch (e: TimeoutException) {
            fail("Timeout occurred: ${e.message}")
        } finally {
            executor.shutdownNow()
            System.setOut(originalOut)
        }

        val printedMessage = outputStream.toString().trim()
        assertEquals(expectedOutput, printedMessage)
    }

    private fun setUpForInput(inputFile: String) {
        val drivers: MutableMap<String, Driver> = mutableMapOf()
        val rides: MutableMap<String, Ride> = mutableMapOf()
        val riders: MutableMap<String, Rider> = mutableMapOf()

        val driverStore = DriverStore(drivers)
        val riderStore = RiderStore(riders)
        val rideStore = RideStore(rides)

        val driverManager = DriverManager(driverStore)
        val riderManager = RiderManager(riderStore)
        val driverWithin5KmsMatchStrategy = DriverWithin5KmsMatchStrategy(riderManager, driverManager)
        val rideManager = RideManager(driverWithin5KmsMatchStrategy, rideStore, driverStore)

        val rideSharingManager = RideSharingManager(driverManager, riderManager, rideManager)
        val matches = mutableListOf<String>()
        val commandExecutor = CommandExecutorBuilder(rideSharingManager, matches)
        val logger = ConsoleLogger()

        val commands = InputParser.parseInput(inputFile)
        commandProcessor = CommandProcessor(commands, commandExecutor, logger)
    }
}