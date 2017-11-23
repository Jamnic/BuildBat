package org.buildbat.plugin.cmd.command

import org.buildbat.execution.command.request.ShellCommandCreationCommand
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.command.shell.ShellCommand
import org.buildbat.project.BaseProjects

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