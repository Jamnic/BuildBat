package org.buildbat.plugin.tomcat.container

import org.buildbat.json.EmptyJson
import org.buildbat.json.Json

class EmptyTomcatContainer(
        private val json: Json = EmptyJson()
) : TomcatContainer, Json by json {
    override fun bin() = throw IllegalStateException("There is no such tomcat container!")
    override fun webapps() = throw IllegalStateException("There is no such tomcat container!")
    override fun conf() = throw IllegalStateException("There is no such tomcat container!")
}