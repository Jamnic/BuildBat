package org.buildbat.web.page.tomcat

import org.buildbat.plugin.tomcat.TomcatConfigurations
import org.buildbat.plugin.tomcat.configuration.BaseTomcatConfiguration
import org.buildbat.plugin.tomcat.container.EmptyTomcatContainer
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
                            it.name(),
                            it.json()["port"],
                            if (it.container() is EmptyTomcatContainer)
                                TomcatContainerInfoResponse(containerExists = false)
                            else
                                TomcatContainerInfoResponse(
                                        it.container().name(),
                                        it.container().json()["home"]))
                }
    }
}