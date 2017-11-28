package org.buildbat.web.page.git

import org.buildbat.core.plugin.git.Git
import org.buildbat.core.plugin.git.project.GitProjects
import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.web.page.git.request.CloneRequest
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
    private val taskPool = TaskPoolProvider.INSTANCE.taskPool

    @PostMapping
    fun command(@RequestBody request: GitExecutionRequest) {
        taskPool.add(
                git.execute(request.command, gitProjects.find(request.projectName)))
    }

    // TODO deprecated?
    @PostMapping("/clone")
    fun clone(@RequestBody request: CloneRequest) {
        taskPool.add(
                git.clone(request.repository, gitProjects.directory()))
    }
}