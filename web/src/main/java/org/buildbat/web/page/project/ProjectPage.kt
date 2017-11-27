package org.buildbat.web.page.project

import org.buildbat.core.future.FutureResult
import org.buildbat.json.JsonObject
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.web.page.project.request.AddProjectRequest
import org.buildbat.web.page.project.response.ProjectInfoResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/project/{projectName}")
class ProjectPage {

    private val projects = BaseProjects()

    @GetMapping
    fun get(
            @PathVariable projectName: String
    ): ProjectInfoResponse {
        val project = projects.find(projectName)
        return ProjectInfoResponse(
                project.key(),
                project.directory().path(),
                project.logs().map { logFile -> FutureResult(logFile) },
                project.json().params())
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
        val params = request.customParams.map { entry -> Pair(entry.key, entry.value) }.toTypedArray()

        projects.save(BaseProject(JsonObject(
                "name" to projectName,
                "directory" to request.directory,
                *params)))
    }
}