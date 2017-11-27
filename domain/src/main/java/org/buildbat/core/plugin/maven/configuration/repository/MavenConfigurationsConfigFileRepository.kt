package org.buildbat.core.plugin.maven.configuration.repository

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.plugin.maven.configuration.BaseMavenConfiguration
import org.buildbat.core.plugin.maven.configuration.EmptyMavenConfiguration
import org.buildbat.core.plugin.maven.configuration.MavenConfiguration
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.json.JsonObject

class MavenConfigurationsConfigFileRepository(
        configFile: ConfigFile
) : ConfigFileRepository<MavenConfiguration>(configFile) {

    constructor(configFilePath: String) : this(ConfigFile(configFilePath))

    override fun createFromJson(json: JsonObject) = BaseMavenConfiguration(json)
    override fun createFromName(name: String) = EmptyMavenConfiguration()
}