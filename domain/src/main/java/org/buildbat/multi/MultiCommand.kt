package org.buildbat.multi

import org.buildbat.Future
import org.buildbat.LoggedFuture
import org.buildbat.execution.command.request.ShellCommandCreationCommand
import org.buildbat.execution.executable.LoggedExecutable
import org.buildbat.execution.process.Process
import org.buildbat.filesystem.file.File
import org.buildbat.log.Log
import org.buildbat.log.LogFactory

class MultiCommand {

    fun execute(commands: List<ShellCommandCreationCommand>): LoggedFuture<Any> {
        val logFile = LogFactory().new("Multi")
        return LoggedFuture(
                Log(logFile),
                commands.fold(
                        Future<Any>({}),
                        { future, command -> future.then({ executeCommand(command, logFile) }) }))
    }

    private fun executeCommand(command: ShellCommandCreationCommand, logFile: File): Process {
        return LoggedExecutable(command.createShellCommand(), logFile).execute()
    }
}