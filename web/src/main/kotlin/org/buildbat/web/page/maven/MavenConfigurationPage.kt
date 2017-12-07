package org.buildbat.web.page.maven

import org.buildbat.core.plugin.maven.configuration.MavenConfigurations
import org.buildbat.web.page.maven.response.MavenConfigurationInfoResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/maven/configuration/{configurationName:.+}")
class MavenConfigurationPage {

    private val mavenConfigurations = MavenConfigurations()

    @GetMapping
    fun get(@PathVariable configurationName: String): MavenConfigurationInfoResponse {
        val mavenConfiguration = mavenConfigurations.find(configurationName)

        return MavenConfigurationInfoResponse(
                mavenConfiguration.key(),
                mavenConfiguration.json()["version"],
                mavenConfiguration.json()["home"],
                mavenConfiguration.home().exists())
    }
}