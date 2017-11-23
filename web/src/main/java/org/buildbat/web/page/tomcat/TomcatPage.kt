package org.buildbat.web.page.tomcat

import org.buildbat.LogResponse
import org.buildbat.plugin.tomcat.TomcatConfigurations
import org.buildbat.plugin.war.WarProjects
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

    @PostMapping
    fun command(
            @RequestBody request: TomcatExecutionRequest
    ): LogResponse {
        return tomcatConfigurations
                .find(request.tomcatConfiguration)
                .execute(request.command, warProjects.find(request.projectName))
                .resolve()
    }
}