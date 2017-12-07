package org.buildbat.web.page.tomcat

import org.buildbat.core.plugin.tomcat.Tomcat
import org.buildbat.core.plugin.tomcat.command.TomcatShellCommandCreationCommand
import org.buildbat.core.plugin.tomcat.configuration.TomcatConfigurations
import org.buildbat.core.plugin.tomcat.project.WarProjects
import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.web.page.tomcat.request.TomcatExecutionRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// TODO list of all tomcat machines and it's statuses
// TODO remote tomcats

@RestController
@RequestMapping("/tomcat")
class TomcatPage {

    private val tomcatConfigurations = TomcatConfigurations()
    private val warProjects = WarProjects()
    private val tomcat = Tomcat()
    private val taskPool = TaskPoolProvider.taskPool

    @PostMapping
    fun command(@RequestBody request: TomcatExecutionRequest) {
        val warProject = warProjects.find(request.projectName)
        val tomcatConfiguration = tomcatConfigurations.find(request.tomcatConfiguration)

        taskPool.add(
                tomcat.createTask(
                        TomcatShellCommandCreationCommand(
                                request.command,
                                warProject,
                                tomcatConfiguration)
                                .createShellCommand(),
                        warProject, tomcatConfiguration))
    }
}