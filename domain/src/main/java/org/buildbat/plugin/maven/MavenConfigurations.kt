package org.buildbat.plugin.maven

import org.buildbat.ConfigFile
import org.buildbat.plugin.maven.configuration.BaseMavenConfiguration
import org.buildbat.plugin.maven.configuration.EmptyMavenConfiguration
import org.buildbat.plugin.maven.configuration.MavenConfiguration

class MavenConfigurations(
        private val configFile: ConfigFile = ConfigFile("classpath:maven.config.json")
) {
    fun find(name: String): MavenConfiguration {
        val json = configFile.find(name)
        return if (json != null)
            BaseMavenConfiguration(json)
        else
            EmptyMavenConfiguration()
    }

    fun save(configuration: MavenConfiguration) {
        configFile.save(configuration.json())
    }

    fun list(): List<MavenConfiguration> {
        return configFile.list().map { json -> BaseMavenConfiguration(json) }
    }
}