package org.buildbat.core.plugin.multi

import org.buildbat.core.futuretask.BaseFutureTask
import org.buildbat.core.futuretask.FutureTask
import org.buildbat.core.log.LogFactory
import org.buildbat.core.execution.command.request.ShellCommandCreationCommand
import org.buildbat.core.execution.executable.LoggedExecutable

class MultiCommand {

    fun createTask(commands: List<ShellCommandCreationCommand>): FutureTask<Any> {
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