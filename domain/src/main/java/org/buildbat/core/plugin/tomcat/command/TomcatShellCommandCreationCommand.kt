package org.buildbat.core.plugin.tomcat.command

import org.buildbat.execution.command.request.ShellCommandCreationCommand
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.command.shell.ShellCommand
import org.buildbat.core.plugin.tomcat.configuration.TomcatConfigurations
import org.buildbat.core.plugin.tomcat.project.WarProjects

class TomcatShellCommandCreationCommand(
        private val command: String,
        private val projectName: String,
        private val tomcatConfiguration: String,
        private val tomcatConfigurations: TomcatConfigurations = TomcatConfigurations(),
        private val warProjects: WarProjects = WarProjects()
) : ShellCommandCreationCommand {

    override fun createShellCommand(): ShellCommand {
        val tomcatConfiguration = tomcatConfigurations.find(tomcatConfiguration)
        return ParametrizedShellCommand(
                ParametrizedShellCommand(
                        TomcatShellCommand(command, tomcatConfiguration.tomcatContainer().home()),
                        tomcatConfiguration),
                warProjects.find(projectName))
    }
}