package org.buildbat.core.file

import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.json.JsonObject
import java.util.*

// TODO - bad design
class PomProperties(
        private val json: JsonObject
) {
    private val prop: Properties = Properties()

    operator fun get(key: String): String {
        return if (json[key] == "") load(json, key) else json[key]
    }

    private fun load(json: JsonObject, key: String): String {
        val reader = BaseProject(json)
                .directory()
                .cd("target")
                .cd("maven-archiver")
                .file("pom.properties")
                .realFile()
                .reader()
        prop.load(reader)
        reader.close()
        return prop[key] as String
    }
}