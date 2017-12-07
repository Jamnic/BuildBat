package org.buildbat.web.page.git

import org.buildbat.core.plugin.git.Git
import org.buildbat.core.plugin.git.command.GitShellCommandCreationCommand
import org.buildbat.core.plugin.git.project.GitProjects
import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.web.page.git.request.GitExecutionRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// TODO pull changes daemon
// TODO detect changes daemon

@RestController
@RequestMapping("/git")
class GitPage {

    private val git = Git()
    private val gitProjects = GitProjects()
    private val taskPool = TaskPoolProvider.taskPool

    @PostMapping
    fun command(@RequestBody request: GitExecutionRequest) {
        val gitProject = gitProjects.find(request.projectName)
        taskPool.add(
                git.createTask(
                        GitShellCommandCreationCommand(
                                request.command,
                                gitProject)
                                .createShellCommand(),
                        gitProject))
    }
}