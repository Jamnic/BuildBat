package org.buildbat.plugin.git

import org.buildbat.Future
import org.buildbat.LoggedFuture
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.executable.LoggedExecutable
import org.buildbat.filesystem.directory.Directory
import org.buildbat.log.Log
import org.buildbat.log.LogFactory
import org.buildbat.multi.MultiCommand
import org.buildbat.plugin.git.command.GitShellCommand
import org.buildbat.plugin.git.command.GitShellCommandCreationCommand
import org.buildbat.plugin.git.project.BaseGitProject
import org.buildbat.plugin.git.project.GitProject
import org.buildbat.project.BaseProject

class Git(
        private val log: LogFactory = LogFactory()
) {

    fun execute(command: String, gitProject: GitProject): LoggedFuture<GitProject> {
        val logFile = log.new(gitProject.name())
        val log = Log(logFile)
        gitProject.addLog(log)
        return LoggedFuture(
                log,
                Future({ GitShellCommand(command, gitProject.directory()) })
                        .then { ParametrizedShellCommand(it, gitProject) }
                        .then { LoggedExecutable(it, logFile) }
                        .then { it.execute() }
                        .then { gitProject })
    }

    // TODO to refactor - move somewhere else or use execute command?
    fun clone(repository: String, directory: Directory): LoggedFuture<Unit> {
        val projectName = generateName(repository)

        val project = BaseGitProject(
                BaseProject(
                        projectName,
                        directory.cd(projectName).mkdir()),
                repository)

        val commands = listOf(
                GitShellCommandCreationCommand("init", projectName),
                GitShellCommandCreationCommand("remote add origin $repository", projectName),
                GitShellCommandCreationCommand("config --local credential.helper store", projectName),
                GitShellCommandCreationCommand("config --local core.longpaths true", projectName),
                GitShellCommandCreationCommand("fetch --all", projectName),
                GitShellCommandCreationCommand("checkout master", projectName))

        return MultiCommand()
                .execute(commands)
                .map({ GitProjects().save(project) })
    }

    private fun generateName(repository: String): String {
        return repository
                .substringBefore(".git")
                .substringAfterLast("/")
    }
}