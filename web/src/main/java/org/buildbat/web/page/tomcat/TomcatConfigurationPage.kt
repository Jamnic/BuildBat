package org.buildbat.web.page.tomcat

import org.buildbat.plugin.tomcat.TomcatConfigurations
import org.buildbat.plugin.tomcat.container.EmptyTomcatContainer
import org.buildbat.web.page.tomcat.response.TomcatConfigurationInfoResponse
import org.buildbat.web.page.tomcat.response.TomcatContainerInfoResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tomcat/configuration/{configurationName:.+}")
class TomcatConfigurationPage {

    private val tomcatConfigurations = TomcatConfigurations()

    @GetMapping
    fun get(@PathVariable configurationName: String): TomcatConfigurationInfoResponse {
        val tomcatConfiguration = tomcatConfigurations.find(configurationName)

        val tomcatContainer = tomcatConfiguration.container()

        return TomcatConfigurationInfoResponse(
                tomcatConfiguration.name(),
                tomcatConfiguration.json()["port"],
                if (tomcatContainer is EmptyTomcatContainer)
                    TomcatContainerInfoResponse(containerExists = false)
                else
                    TomcatContainerInfoResponse(
                            tomcatContainer.name(),
                            tomcatContainer.json()["home"]))
    }
}