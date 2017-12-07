package org.buildbat.core.plugin.tomcat

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.executable.LoggedExecutable
import org.buildbat.core.futuretask.BaseFutureTask
import org.buildbat.core.futuretask.FutureTask
import org.buildbat.core.log.BaseLogFactory
import org.buildbat.core.log.LogFactory
import org.buildbat.core.plugin.tomcat.configuration.TomcatConfiguration
import org.buildbat.core.plugin.tomcat.project.WarProject

class Tomcat(
        private val logFactory: LogFactory = BaseLogFactory()
) {

    fun createTask(
            command: ShellCommand,
            project: WarProject,
            tomcatConfiguration: TomcatConfiguration
    ): FutureTask<TomcatConfiguration> {

        val logFile = logFactory.new(project.key())
        project.addLog(logFile)
        return BaseFutureTask({ command })
                .then { LoggedExecutable(it, logFile) }
                .then { it.execute() }
                .then { tomcatConfiguration }
    }
}