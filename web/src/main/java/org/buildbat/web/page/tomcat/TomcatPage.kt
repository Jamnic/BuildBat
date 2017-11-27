package org.buildbat.web.page.tomcat

import org.buildbat.core.future.FutureResult
import org.buildbat.core.plugin.tomcat.Tomcat
import org.buildbat.core.plugin.tomcat.configuration.TomcatConfigurations
import org.buildbat.core.plugin.tomcat.project.WarProjects
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

    @PostMapping
    fun command(
            @RequestBody request: TomcatExecutionRequest
    ): FutureResult {
        return tomcat
                .execute(
                        request.command,
                        warProjects.find(request.projectName),
                        tomcatConfigurations.find(request.tomcatConfiguration))
                .resolve()
    }
}