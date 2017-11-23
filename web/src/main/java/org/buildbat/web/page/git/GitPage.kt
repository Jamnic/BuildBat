package org.buildbat.web.page.git

import org.buildbat.LogResponse
import org.buildbat.plugin.git.Git
import org.buildbat.plugin.git.GitProjects
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

    @PostMapping
    fun command(
            @RequestBody request: GitExecutionRequest
    ): LogResponse {
        return git
                .execute(request.command, gitProjects.find(request.projectName))
                .resolve({ future -> gitProjects.save(future) })
    }

    // TODO deprecated?
    @PostMapping("/clone")
    fun clone(
            @RequestBody request: CloneRequest
    ): LogResponse {
        return git
                .clone(request.repository, gitProjects.directory())
                .resolve()
    }
}