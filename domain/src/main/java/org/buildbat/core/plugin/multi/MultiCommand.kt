package org.buildbat.core.plugin.multi

import org.buildbat.core.future.Future
import org.buildbat.core.future.LoggedFuture
import org.buildbat.execution.command.request.ShellCommandCreationCommand
import org.buildbat.execution.executable.LoggedExecutable
import org.buildbat.execution.process.Process
import org.buildbat.filesystem.file.File
import org.buildbat.core.log.LogFile
import org.buildbat.core.log.LogFactory

class MultiCommand {

    fun execute(commands: List<ShellCommandCreationCommand>): LoggedFuture<Any> {
        val logFile = LogFactory().new("Multi")
        return LoggedFuture(
                logFile,
                commands.fold(
                        Future<Any>({}),
                        { futureAcc, command ->
                            futureAcc
                                    .then({ LoggedExecutable(command.createShellCommand(), logFile) })
                                    .then({ it.execute() })
                        }))
    }
}