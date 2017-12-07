package org.buildbat.core.plugin.cmd

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.executable.LoggedExecutable
import org.buildbat.core.futuretask.BaseFutureTask
import org.buildbat.core.futuretask.FutureTask
import org.buildbat.core.log.BaseLogFactory
import org.buildbat.core.log.LogFactory
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.core.plugin.project.Project
import org.buildbat.core.plugin.project.SaveableBaseProjects

class Cmd(
        private val logFactory: LogFactory = BaseLogFactory(),
        private val projects: SaveableBaseProjects = BaseProjects()
) {

    fun createTask(command: ShellCommand, project: Project): FutureTask<Project> {
        val logFile = logFactory.new(project.key())
        project.addLog(logFile)

        return BaseFutureTask({ command })
                .then { LoggedExecutable(it, logFile) }
                .then { it.execute() }
                .then { projects.save(project) }
    }
}