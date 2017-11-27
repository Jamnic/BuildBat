package org.buildbat.core.plugin.git

import org.buildbat.core.future.Future
import org.buildbat.core.future.LoggedFuture
import org.buildbat.execution.command.shell.ParametrizedShellCommand
import org.buildbat.execution.executable.LoggedExecutable
import org.buildbat.filesystem.directory.Directory
import org.buildbat.core.log.LogFactory
import org.buildbat.core.plugin.multi.MultiCommand
import org.buildbat.core.plugin.git.command.GitShellCommand
import org.buildbat.core.plugin.git.command.GitShellCommandCreationCommand
import org.buildbat.core.plugin.git.project.BaseGitProject
import org.buildbat.core.plugin.git.project.GitProject
import org.buildbat.core.plugin.git.project.GitProjects
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.Project

class Git(
        private val logFactory: LogFactory = LogFactory(),
        private val gitProjects: GitProjects = GitProjects()
) {

    fun execute(command: String, gitProject: GitProject): LoggedFuture<GitProject> {
        val logFile = logFactory.new(gitProject.key())
        gitProject.addLog(logFile)
        return LoggedFuture(
                logFile,
                Future({ GitShellCommand(command, gitProject.directory()) })
                        .then { ParametrizedShellCommand(it, gitProject) }
                        .then { LoggedExecutable(it, logFile) }
                        .then { it.execute() }
                        .then { gitProjects.save(gitProject) })
    }

    // TODO to refactor - move somewhere else or use execute command?
    fun clone(repository: String, directory: Directory): LoggedFuture<Project> {
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
                .then({ GitProjects().save(project) })
    }

    private fun generateName(repository: String): String {
        return repository
                .substringBefore(".git")
                .substringAfterLast("/")
    }
}