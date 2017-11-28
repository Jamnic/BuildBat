package org.buildbat.core.plugin.cmd.command

import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.execution.command.request.ShellCommandCreationCommand
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.command.shell.ShellCommand

class CmdShellCommandCreationCommand(
        private val command: String,
        private val projectName: String,
        private val projects: BaseProjects = BaseProjects()
) : ShellCommandCreationCommand {

    override fun createShellCommand(): ShellCommand {
        val project = projects.find(projectName)
        return ParametrizedShellCommand(
                CmdShellCommand(command, project.directory()),
                project)
    }
}