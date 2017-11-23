package org.buildbat.plugin.cmd

import org.buildbat.Future
import org.buildbat.LoggedFuture
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.executable.LoggedExecutable
import org.buildbat.log.Log
import org.buildbat.log.LogFactory
import org.buildbat.plugin.cmd.command.CmdShellCommand
import org.buildbat.project.Project

class Cmd(
        private val log: LogFactory = LogFactory()
) {

    fun execute(command: String, project: Project): LoggedFuture<Project> {
        val logFile = log.new(project.name())
        val log = Log(logFile)
        project.addLog(log)
        return LoggedFuture(
                log,
                Future({ CmdShellCommand(command, project.directory()) })
                        .then { ParametrizedShellCommand(it, project) }
                        .then { LoggedExecutable(it, logFile) }
                        .then { project })
    }
}