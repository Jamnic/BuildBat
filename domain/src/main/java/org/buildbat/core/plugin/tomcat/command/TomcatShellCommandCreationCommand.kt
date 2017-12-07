package org.buildbat.core.plugin.tomcat.command

import org.buildbat.core.execution.command.request.ShellCommandCreationCommand
import org.buildbat.core.execution.command.shell.parametrized.ParametrizedShellCommand
import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.command.shell.submodule.SubmoduleParametrizedShellCommand
import org.buildbat.core.plugin.tomcat.configuration.TomcatConfiguration
import org.buildbat.core.plugin.tomcat.configuration.TomcatConfigurations
import org.buildbat.core.plugin.tomcat.project.WarProject
import org.buildbat.core.plugin.tomcat.project.WarProjects

class TomcatShellCommandCreationCommand(
        private val command: String,
        private val warProject: WarProject,
        private val tomcatConfiguration: TomcatConfiguration
) : ShellCommandCreationCommand {

    constructor(
            command: String,
            warProjectName: String,
            tomcatConfigurationName: String
    ) : this(
            command,
            WarProjects().find(warProjectName),
            TomcatConfigurations().find(tomcatConfigurationName))

    override fun createShellCommand(): ShellCommand {
        val container = tomcatConfiguration.tomcatContainer()

        return ParametrizedShellCommand(
                ParametrizedShellCommand(
                        ParametrizedShellCommand(
                                SubmoduleParametrizedShellCommand(
                                        TomcatShellCommand(
                                                command,
                                                container.home()),
                                        warProject),
                                warProject),
                        container),
                tomcatConfiguration)
    }
}