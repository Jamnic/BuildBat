package org.buildbat.core.plugin.maven.command

import org.buildbat.core.plugin.maven.project.MavenProjects
import org.buildbat.execution.command.request.ShellCommandCreationCommand
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.command.shell.ShellCommand

class MavenShellCommandCreationCommand(
        private val command: String,
        private val projectName: String,
        private val mavenProjects: MavenProjects = MavenProjects()
) : ShellCommandCreationCommand {

    override fun createShellCommand(): ShellCommand {
        val mavenProject = mavenProjects.find(projectName)
        return ParametrizedShellCommand(
                MavenShellCommand(command, mavenProject),
                mavenProject)
    }
}
