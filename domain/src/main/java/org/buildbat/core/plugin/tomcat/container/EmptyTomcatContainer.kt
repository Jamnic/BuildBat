package org.buildbat.core.plugin.tomcat.container

import org.buildbat.json.EmptyJsonEntity
import org.buildbat.json.JsonEntity

class EmptyTomcatContainer(
        private val json: JsonEntity = EmptyJsonEntity()
) : TomcatContainer, JsonEntity by json {
    override fun home() = throw IllegalStateException("There is no such tomcat container!")
}