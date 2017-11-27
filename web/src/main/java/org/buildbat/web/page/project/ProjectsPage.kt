package org.buildbat.web.page.project

import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.web.page.project.response.ProjectInfoResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/projects")
class ProjectsPage {

    private val projects = BaseProjects()

    @GetMapping
    fun list(): List<ProjectInfoResponse> {
        return projects
                .list()
                .map { ProjectInfoResponse(it.key(), it.directory().path(), params = it.json().params()) }
    }
}