package org.buildbat.core.plugin.maven.command

import org.buildbat.core.execution.command.request.ShellCommandCreationCommand
import org.buildbat.core.execution.command.shell.parametrized.ParametrizedShellCommand
import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.command.shell.submodule.SubmoduleParametrizedShellCommand
import org.buildbat.core.plugin.maven.project.BaseMavenProject
import org.buildbat.core.plugin.maven.project.MavenProject
import org.buildbat.core.plugin.maven.project.MavenProjects
import org.buildbat.core.plugin.project.EmptyProject

class MavenShellCommandCreationCommand(
        private val command: String,
        private val mavenProject: MavenProject,
        private val submoduleName: String = ""
) : ShellCommandCreationCommand {

    constructor(
            command: String,
            mavenProjectName: String
    ) : this(command,
            MavenProjects().find(mavenProjectName))

    override fun createShellCommand(): ShellCommand {
        val submodule = mavenProject.module(submoduleName)
        return if (submodule is EmptyProject)
            buildCommand(mavenProject)
        else
            buildCommand(BaseMavenProject(submodule))
    }

    private fun buildCommand(mavenProject: MavenProject): ParametrizedShellCommand {
        return ParametrizedShellCommand(
                SubmoduleParametrizedShellCommand(
                        MavenShellCommand(
                                command,
                                mavenProject),
                        mavenProject),
                mavenProject)
    }
}
