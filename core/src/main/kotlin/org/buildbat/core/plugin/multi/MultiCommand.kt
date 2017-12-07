package org.buildbat.core.plugin.multi

import org.buildbat.core.execution.command.request.ShellCommandCreationCommand
import org.buildbat.core.execution.executable.LoggedExecutable
import org.buildbat.core.futuretask.BaseFutureTask
import org.buildbat.core.futuretask.FutureTask
import org.buildbat.core.log.BaseLogFactory
import org.buildbat.core.log.LogFactory

class MultiCommand(
        private val logFactory: LogFactory = BaseLogFactory()
) {

    fun createTask(commands: List<ShellCommandCreationCommand>): FutureTask<Any> {
        val logFile = logFactory.new("Multi")
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