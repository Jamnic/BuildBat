package org.buildbat.core.plugin.git

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.executable.LoggedExecutable
import org.buildbat.core.futuretask.BaseFutureTask
import org.buildbat.core.futuretask.FutureTask
import org.buildbat.core.log.BaseLogFactory
import org.buildbat.core.log.LogFactory
import org.buildbat.core.plugin.git.project.GitProject
import org.buildbat.core.plugin.git.project.GitProjects

class Git(
        private val logFactory: LogFactory = BaseLogFactory()
) {

    fun createTask(command: ShellCommand, gitProject: GitProject): FutureTask<GitProject> {
        val logFile = logFactory.new(gitProject.key())
        gitProject.addLog(logFile)
        return BaseFutureTask({ command })
                .then { LoggedExecutable(it, logFile) }
                .then { it.execute() }
                .then { GitProjects().save(gitProject) }
    }
}