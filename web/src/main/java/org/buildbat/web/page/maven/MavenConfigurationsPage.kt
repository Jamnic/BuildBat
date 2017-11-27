package org.buildbat.web.page.maven

import org.buildbat.core.plugin.maven.configuration.MavenConfigurations
import org.buildbat.core.plugin.maven.configuration.BaseMavenConfiguration
import org.buildbat.web.page.maven.request.NewMavenConfigurationRequest
import org.buildbat.web.page.maven.response.MavenConfigurationInfoResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/maven/configuration")
class MavenConfigurationsPage {

    private val mavenConfigurations = MavenConfigurations()

    @PutMapping
    fun put(@RequestBody request: NewMavenConfigurationRequest) {
        mavenConfigurations
                .save(BaseMavenConfiguration(request.name, request.version, request.home))
    }

    @GetMapping
    fun get(): List<MavenConfigurationInfoResponse> {
        return mavenConfigurations
                .list()
                .map {
                    MavenConfigurationInfoResponse(
                            it.key(),
                            it.json()["version"],
                            it.json()["home"],
                            it.home().exists())
                }
    }
}