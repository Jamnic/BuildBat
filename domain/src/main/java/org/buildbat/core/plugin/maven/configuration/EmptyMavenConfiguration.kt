package org.buildbat.core.plugin.maven.configuration

import org.buildbat.json.EmptyJsonEntity
import org.buildbat.json.JsonEntity

class EmptyMavenConfiguration(
        private val json: JsonEntity = EmptyJsonEntity()
) : MavenConfiguration, JsonEntity by json {
    override fun home() = throw IllegalStateException("There is no such maven configuration!")
    override fun mvnExecutable() = throw IllegalStateException("There is no such maven configuration!")
}