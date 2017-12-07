package org.buildbat.core.plugin.tomcat.configuration.repository

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.plugin.tomcat.configuration.BaseTomcatConfiguration
import org.buildbat.core.plugin.tomcat.configuration.EmptyTomcatConfiguration
import org.buildbat.core.plugin.tomcat.configuration.TomcatConfiguration
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.json.JsonObject

class TomcatConfigurationsConfigFileRepository(
        configFile: ConfigFile
) : ConfigFileRepository<TomcatConfiguration>(configFile) {

    constructor(configFilePath: String) : this(ConfigFile(configFilePath))

    override fun createFromJson(json: JsonObject) = BaseTomcatConfiguration(json)
    override fun createFromName(name: String) = EmptyTomcatConfiguration()
}