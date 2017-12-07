package org.buildbat.core.plugin.maven

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.executable.LoggedExecutable
import org.buildbat.core.futuretask.BaseFutureTask
import org.buildbat.core.futuretask.FutureTask
import org.buildbat.core.log.BaseLogFactory
import org.buildbat.core.log.LogFactory
import org.buildbat.core.plugin.maven.project.MavenProject
import org.buildbat.core.plugin.maven.project.MavenProjects

class Maven(
        private val logFactory: LogFactory = BaseLogFactory()
) {

    fun createTask(command: ShellCommand, mavenProject: MavenProject): FutureTask<MavenProject> {
        val logFile = logFactory.new(mavenProject.key())
        mavenProject.addLog(logFile)
        return BaseFutureTask({ command })
                .then { LoggedExecutable(it, logFile) }
                .then { it.execute() }
                .then { MavenProjects().save(mavenProject) }
    }
}