package org.buildbat.core.plugin.git.command

import org.buildbat.execution.command.request.ShellCommandCreationCommand
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.command.shell.ShellCommand
import org.buildbat.core.plugin.git.project.GitProjects


class GitShellCommandCreationCommand(
        private val command: String,
        private val projectName: String,
        private val gitProjects: GitProjects = GitProjects()
) : ShellCommandCreationCommand {

    override fun createShellCommand(): ShellCommand {
        val gitProject = gitProjects.find(projectName)
        return ParametrizedShellCommand(
                GitShellCommand(command, gitProject.directory()),
                gitProject)
    }
}