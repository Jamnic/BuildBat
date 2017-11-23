package org.buildbat.web.page.maven

import org.buildbat.LogResponse
import org.buildbat.plugin.maven.Maven
import org.buildbat.plugin.maven.MavenProjects
import org.buildbat.web.page.maven.request.MavenExecutionRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// TODO building multimodule projects
// TODO building specified parts of multimodule projects
// TODO testing projects

// TODO BuildBat should calculate hash for each project.
// TODO MavenProject should detect whether project files changed through calculating and comparing hashes.
// TODO If true, this part of project should be rebuild

// TODO build in background - building temporary jars, which may be replaced on demand

@RestController
@RequestMapping("/maven")
class MavenPage {

    private val maven = Maven()
    private val mavenProjects = MavenProjects()

    @PostMapping
    fun command(
            @RequestBody request: MavenExecutionRequest
    ): LogResponse {
        val mavenProject = mavenProjects.find(request.projectName)
        return maven
                .execute(request.command, mavenProject)
                .resolve()
    }
}