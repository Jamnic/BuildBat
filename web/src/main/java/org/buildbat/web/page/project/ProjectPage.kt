package org.buildbat.web.page.project

import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.json.JsonObject
import org.buildbat.web.page.project.request.AddProjectRequest
import org.buildbat.web.page.project.response.LogInfoResponse
import org.buildbat.web.page.project.response.ProjectInfoResponse
import org.buildbat.web.page.project.response.ProjectLogsInfoResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/project/{projectName}")
class ProjectPage {

    private val projects = BaseProjects()

    @GetMapping
    fun get(@PathVariable projectName: String): ProjectInfoResponse {
        val project = projects.find(projectName)
        return ProjectInfoResponse(
                project.key(),
                project.directory().path(),
                project.logs().map { logFile -> LogInfoResponse(logFile) },
                project.json().params())
    }

    @GetMapping("/logs")
    fun getLogs(@PathVariable projectName: String): ProjectLogsInfoResponse {
        val project = projects.find(projectName)
        return ProjectLogsInfoResponse(
                project.logs().map { logFile -> LogInfoResponse(logFile) })
    }

    @DeleteMapping
    fun delete(
            @PathVariable projectName: String
    ) {
        projects.remove(projectName)
    }

    @PutMapping
    fun add(
            @PathVariable projectName: String,
            @RequestBody request: AddProjectRequest
    ) {
        val params = request.customParams.map { entry -> kotlin.Pair(entry.key, entry.value) }.toTypedArray()

        projects.save(BaseProject(JsonObject(
                "name" to projectName,
                "directory" to request.directory,
                *params)))
    }
}