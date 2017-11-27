package org.buildbat.core.log.configuration.repository

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.log.configuration.LogConfiguration
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.json.JsonObject

class LogConfigurationsConfigFileRepository(
        configFile: ConfigFile
) : ConfigFileRepository<LogConfiguration>(configFile) {

    constructor(configFilePath : String) : this(ConfigFile(configFilePath))

    override fun createFromJson(json: JsonObject) = LogConfiguration(json)
    override fun createFromName(name: String) = LogConfiguration(name)
}