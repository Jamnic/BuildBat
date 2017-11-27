package org.buildbat.core.plugin.tomcat

import org.buildbat.core.future.Future
import org.buildbat.core.future.LoggedFuture
import org.buildbat.core.log.LogFactory
import org.buildbat.core.plugin.tomcat.command.TomcatShellCommand
import org.buildbat.core.plugin.tomcat.configuration.TomcatConfiguration
import org.buildbat.core.plugin.tomcat.project.WarProject
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.executable.LoggedExecutable

class Tomcat(
        private val logFactory: LogFactory = LogFactory()
) {

    fun execute(command: String, project: WarProject, tomcatConfiguration: TomcatConfiguration): LoggedFuture<TomcatConfiguration> {
        val logFile = logFactory.new(project.key())
        val tomcatContainer = tomcatConfiguration.tomcatContainer()
        project.addLog(logFile)
        return LoggedFuture(
                logFile,
                Future({ TomcatShellCommand(command, tomcatContainer.home()) })
                        .then { ParametrizedShellCommand(it, project) }
                        .then { ParametrizedShellCommand(it, tomcatContainer) }
                        .then { ParametrizedShellCommand(it, tomcatConfiguration) }
                        .then { LoggedExecutable(it, logFile) }
                        .then { it.execute() }
                        .then { tomcatConfiguration })
    }

}