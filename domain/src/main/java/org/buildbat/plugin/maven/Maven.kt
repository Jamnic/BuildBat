package org.buildbat.plugin.maven

import org.buildbat.Future
import org.buildbat.LoggedFuture
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.executable.LoggedExecutable
import org.buildbat.log.Log
import org.buildbat.log.LogFactory
import org.buildbat.plugin.maven.command.MavenShellCommand
import org.buildbat.plugin.maven.project.MavenProject

class Maven(
        private val log: LogFactory = LogFactory()
) {

    fun execute(command: String, mavenProject: MavenProject): LoggedFuture<MavenProject> {
        val logFile = log.new(mavenProject.name())
        val log = Log(logFile)
        mavenProject.addLog(log)
        return LoggedFuture(
                log,
                Future({ MavenShellCommand(command, mavenProject) })
                        .then { ParametrizedShellCommand(it, mavenProject) }
                        .then { LoggedExecutable(it, logFile) }
                        .then { it.execute() }
                        .then { mavenProject }
                        .then {
                            MavenProjects().save(it)
                            it
                        })
    }
}