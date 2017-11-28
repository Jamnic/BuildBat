package org.buildbat.web.page.maven

import org.buildbat.core.plugin.maven.Maven
import org.buildbat.core.plugin.maven.project.MavenProjects
import org.buildbat.core.task.TaskPoolProvider
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
    private val taskPool = TaskPoolProvider.INSTANCE.taskPool

    @PostMapping
    fun command(
            @RequestBody request: MavenExecutionRequest
    ) {
        val mavenProject = mavenProjects.find(request.projectName)
        taskPool.add(
                maven.execute(request.command, mavenProject))
    }
}