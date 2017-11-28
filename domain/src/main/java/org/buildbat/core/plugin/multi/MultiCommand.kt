package org.buildbat.core.plugin.multi

import org.buildbat.core.future.BaseFutureTask
import org.buildbat.core.future.FutureTask
import org.buildbat.core.log.LogFactory
import org.buildbat.execution.command.request.ShellCommandCreationCommand
import org.buildbat.execution.executable.LoggedExecutable

class MultiCommand {

    fun execute(commands: List<ShellCommandCreationCommand>): FutureTask<Any> {
        val logFile = LogFactory().new("Multi")
        return commands.fold(
                BaseFutureTask<Any>({}),
                { futureAcc, command ->
                    futureAcc
                            .then({ LoggedExecutable(command.createShellCommand(), logFile) })
                            .then({ it.execute() })
                            as BaseFutureTask<Any>
                })
    }
}