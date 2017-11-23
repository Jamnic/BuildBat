package org.buildbat.plugin.tomcat

import org.buildbat.ConfigFile
import org.buildbat.plugin.tomcat.container.BaseTomcatContainer
import org.buildbat.plugin.tomcat.container.EmptyTomcatContainer
import org.buildbat.plugin.tomcat.container.TomcatContainer

class TomcatContainers(
        private val configFile: ConfigFile = ConfigFile("classpath:tomcat.containers.json")
) {
    fun find(name: String): TomcatContainer {
        val json = configFile.find(name)
        return if (json != null)
            BaseTomcatContainer(json)
        else
            EmptyTomcatContainer()
    }

    fun save(tomcatContainer: TomcatContainer) {
        configFile.save(tomcatContainer.json())
    }

    fun list(): List<TomcatContainer> {
        return configFile.list().map { BaseTomcatContainer(it) }
    }
}