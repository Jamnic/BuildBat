package org.buildbat.plugin.tomcat

import org.buildbat.ConfigFile
import org.buildbat.JsonFile
import org.buildbat.filesystem.file.BaseFile
import org.buildbat.plugin.tomcat.configuration.BaseTomcatConfiguration
import org.buildbat.plugin.tomcat.configuration.EmptyTomcatConfiguration
import org.buildbat.plugin.tomcat.configuration.TomcatConfiguration

class TomcatConfigurations(
        private val configFile: ConfigFile = ConfigFile(JsonFile("name", BaseFile("classpath:tomcat.config.json")))
) {
    fun find(name: String): TomcatConfiguration {
        val json = configFile.find(name)
        return if (json != null)
            BaseTomcatConfiguration(json)
        else
            EmptyTomcatConfiguration()
    }

    fun save(tomcatConfiguration: TomcatConfiguration) {
        configFile.save(tomcatConfiguration.json())
    }

    fun list(): List<BaseTomcatConfiguration> {
        return configFile.list().map { json -> BaseTomcatConfiguration(json) }
    }
}