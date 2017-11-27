package org.buildbat.core.plugin.tomcat.container

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.json.JsonObject

class TomcatContainers(
        configFile: ConfigFile = ConfigFile("classpath:tomcat.containers.json")
) : ConfigFileRepository<TomcatContainer>(configFile) {

    override fun createFromJson(json: JsonObject) = BaseTomcatContainer(json)
    override fun createFromName(name: String) = EmptyTomcatContainer()
}