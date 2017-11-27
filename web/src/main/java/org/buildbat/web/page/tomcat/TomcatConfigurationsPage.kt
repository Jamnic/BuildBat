package org.buildbat.web.page.tomcat

import org.buildbat.core.plugin.tomcat.configuration.TomcatConfigurations
import org.buildbat.core.plugin.tomcat.configuration.BaseTomcatConfiguration
import org.buildbat.core.plugin.tomcat.container.EmptyTomcatContainer
import org.buildbat.web.page.tomcat.request.NewTomcatConfigurationRequest
import org.buildbat.web.page.tomcat.response.TomcatConfigurationInfoResponse
import org.buildbat.web.page.tomcat.response.TomcatContainerInfoResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tomcat/configuration")
class TomcatConfigurationsPage {

    private val tomcatConfigurations = TomcatConfigurations()

    @PutMapping
    fun put(@RequestBody request: NewTomcatConfigurationRequest) {
        tomcatConfigurations
                .save(BaseTomcatConfiguration(request.name, request.tomcatContainerName, request.port))
    }

    @GetMapping
    fun get(): List<TomcatConfigurationInfoResponse> {
        return tomcatConfigurations.list()
                .map {
                    TomcatConfigurationInfoResponse(
                            it.key(),
                            it.json()["port"],
                            it.json()["serverXml"],
                            if (it.tomcatContainer() is EmptyTomcatContainer)
                                TomcatContainerInfoResponse(containerExists = false)
                            else
                                TomcatContainerInfoResponse(
                                        it.tomcatContainer().key(),
                                        it.tomcatContainer().json()["home"]))
                }
    }
}