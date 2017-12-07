package org.buildbat.core.plugin.cmd

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.execution.executable.LoggedExecutable
import org.buildbat.core.futuretask.BaseFutureTask
import org.buildbat.core.futuretask.FutureTask
import org.buildbat.core.log.LogFactory
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.core.plugin.project.Project

class Cmd {

    fun createTask(command: ShellCommand, project: Project): FutureTask<Project> {
        val logFile = LogFactory().new(project.key())
        project.addLog(logFile)
        return BaseFutureTask({ command })
                .then { LoggedExecutable(it, logFile) }
                .then { BaseProjects().save(project) }
    }
}