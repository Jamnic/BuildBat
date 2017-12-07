package org.buildbat.core.plugin.tomcat.configuration

import org.buildbat.json.EmptyJsonEntity
import org.buildbat.json.JsonEntity

class EmptyTomcatConfiguration(
        private val json: JsonEntity = EmptyJsonEntity()
) : TomcatConfiguration, JsonEntity by json {
    override fun tomcatContainer() = throw IllegalStateException("There is no such Tomcat Container!")
}