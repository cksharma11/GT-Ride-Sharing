import command.CommandProcessor
import command.executor.CommandExecutorBuilder
import driver.DriverManager
import driver.DriverStore
import driver.entity.Driver
import logger.ConsoleLogger
import parser.Command
import parser.InputParser
import ride.RideManager
import ride.RideStore
import ride.entity.Ride
import ride.strategy.DriverWithin5KmsMatchStrategy
import ride_sharing.RideSharingManager
import rider.Rider
import rider.RiderManager
import rider.RiderStore

fun main(args: Array<String>) {
    val inputFile = "input-2"

    val drivers: MutableMap<String, Driver> = mutableMapOf()
    val rides: MutableMap<String, Ride> = mutableMapOf()
    val riders: MutableMap<String, Rider> = mutableMapOf()

    val driverStore = DriverStore(drivers)
    val riderStore = RiderStore(riders)
    val rideStore = RideStore(rides)

    val driverManager = DriverManager(driverStore)
    val riderManager = RiderManager(riderStore)
    val driverWithin5KmsMatchStrategy = DriverWithin5KmsMatchStrategy(riderManager, driverManager)
    val rideManager = RideManager(driverWithin5KmsMatchStrategy, rideStore)

    val rideSharingManager = RideSharingManager(driverManager, riderManager, rideManager)
    val commandExecutor = CommandExecutorBuilder(rideSharingManager)
    val logger = ConsoleLogger()

    val commands = InputParser.parseInput(inputFile)
    val commandProcessor = CommandProcessor(commands, commandExecutor, logger)

    commandProcessor.processCommands()
}