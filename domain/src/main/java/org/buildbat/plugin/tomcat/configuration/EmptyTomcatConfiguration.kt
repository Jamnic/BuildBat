package org.buildbat.plugin.tomcat.configuration

import org.buildbat.json.EmptyJson
import org.buildbat.json.Json
import org.buildbat.plugin.war.project.WarProject

class EmptyTomcatConfiguration(
        private val json: Json = EmptyJson()
) : TomcatConfiguration, Json by json {
    override fun execute(command: String, project: WarProject) = throw IllegalStateException("There is no such Tomcat Container!")
    override fun container() = throw IllegalStateException("There is no such Tomcat Container!")
}