package command.executor

import ride_sharing.RideSharingManager

abstract class AbstractCommandExecutor(val rideSharingManager: RideSharingManager) : CommandExecutor