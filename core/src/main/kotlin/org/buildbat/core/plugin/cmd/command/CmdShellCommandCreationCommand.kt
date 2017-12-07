package org.buildbat.core.plugin.cmd.command

import org.buildbat.core.execution.command.request.ShellCommandCreationCommand
import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.command.shell.parametrized.ParametrizedShellCommand
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.core.plugin.project.Project

class CmdShellCommandCreationCommand(
        private val command: String,
        private val project: Project
) : ShellCommandCreationCommand {

    constructor(
            command: String,
            projectName: String
    ) : this(
            command,
            BaseProjects().find(projectName))

    override fun createShellCommand(): ShellCommand {
        return ParametrizedShellCommand(
                CmdShellCommand(
                        command,
                        project.directory()),
                project)
    }
}