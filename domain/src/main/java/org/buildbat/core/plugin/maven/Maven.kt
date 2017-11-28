package org.buildbat.core.plugin.maven

import org.buildbat.core.future.BaseFutureTask
import org.buildbat.core.future.FutureTask
import org.buildbat.core.log.LogFactory
import org.buildbat.core.plugin.maven.command.MavenShellCommand
import org.buildbat.core.plugin.maven.project.MavenProject
import org.buildbat.core.plugin.maven.project.MavenProjects
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.executable.LoggedExecutable

class Maven(
        private val log: LogFactory = LogFactory()
) {

    fun execute(command: String, mavenProject: MavenProject): FutureTask<MavenProject> {
        val logFile = log.new(mavenProject.key())
        mavenProject.addLog(logFile)
        return BaseFutureTask({ MavenShellCommand(command, mavenProject) })
                .then { ParametrizedShellCommand(it, mavenProject) }
                .then { LoggedExecutable(it, logFile) }
                .then { it.execute() }
                .then { MavenProjects().save(mavenProject) }
    }
}