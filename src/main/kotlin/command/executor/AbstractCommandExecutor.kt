package command.executor

import command.HelperArgs
import ride_sharing.RideSharingManager

abstract class AbstractCommandExecutor(
    val rideSharingManager: RideSharingManager,
    val helperArgs: HelperArgs
) : CommandExecutor