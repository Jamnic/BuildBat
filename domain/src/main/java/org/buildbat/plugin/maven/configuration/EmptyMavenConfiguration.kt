package org.buildbat.plugin.maven.configuration

import org.buildbat.json.EmptyJson
import org.buildbat.json.Json

class EmptyMavenConfiguration(
        private val json : Json = EmptyJson()
) : MavenConfiguration, Json by json {
    override fun bin() = throw IllegalStateException("There is no such maven configuration!")
    override fun home() = throw IllegalStateException("There is no such maven configuration!")
    override fun mvn() = throw IllegalStateException("There is no such maven configuration!")
}