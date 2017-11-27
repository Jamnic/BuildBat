package org.buildbat.core.plugin.cmd

import org.buildbat.core.future.Future
import org.buildbat.core.future.LoggedFuture
import org.buildbat.core.log.LogFactory
import org.buildbat.core.plugin.cmd.command.CmdShellCommand
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.core.plugin.project.Project
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.executable.LoggedExecutable

class Cmd(
        private val logFactory: LogFactory = LogFactory(),
        private val projects: BaseProjects = BaseProjects()
) {

    fun execute(command: String, project: Project): LoggedFuture<Project> {
        val logFile = logFactory.new(project.key())
        project.addLog(logFile)
        return LoggedFuture(
                logFile,
                Future({ CmdShellCommand(command, project.directory()) })
                        .then { ParametrizedShellCommand(it, project) }
                        .then { LoggedExecutable(it, logFile) }
                        .then { projects.save(project) })
    }
}