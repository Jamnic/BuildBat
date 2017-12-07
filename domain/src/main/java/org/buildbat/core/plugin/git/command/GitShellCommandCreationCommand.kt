package org.buildbat.core.plugin.git.command

import org.buildbat.core.execution.command.request.ShellCommandCreationCommand
import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.command.shell.parametrized.ParametrizedShellCommand
import org.buildbat.core.plugin.git.project.GitProject
import org.buildbat.core.plugin.git.project.GitProjects

class GitShellCommandCreationCommand(
        private val command: String,
        private val gitProject: GitProject
) : ShellCommandCreationCommand {

    constructor(
            command: String,
            gitProjectName: String
    ) : this(
            command,
            GitProjects().find(gitProjectName)
    )

    override fun createShellCommand(): ShellCommand {
        return ParametrizedShellCommand(
                GitShellCommand(
                        command,
                        gitProject.directory()),
                gitProject)
    }
}