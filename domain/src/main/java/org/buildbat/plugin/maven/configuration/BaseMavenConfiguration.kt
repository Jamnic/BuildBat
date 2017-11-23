package org.buildbat.plugin.maven.configuration

import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.BaseJson
import org.buildbat.json.Json
import org.buildbat.json.JsonObject

class BaseMavenConfiguration(
        private val json : Json,
        private val version: String,
        private val home: Directory
) : MavenConfiguration, Json by json {

    constructor(
            json: JsonObject
    ) : this(json["name"], json["version"], json["home"])

    constructor(
            name: String,
            version: String,
            home: String
    ) : this(BaseJson(name), version, Directory(home))

    override fun json(): JsonObject {
        return JsonObject(
                "name" to json.name(),
                "version" to version,
                "home" to home.path())
    }

    override fun bin(): Directory {
        return home.cd("bin")
    }

    override fun home(): Directory {
        return home
    }

    override fun mvn(): String {
        return when {
            bin().file("mvn.cmd").exists() -> bin().path() + "mvn.cmd"
            bin().file("mvn.bat").exists() -> bin().path() + "mvn.bat"
            bin().file("mvn.sh").exists() -> bin().path() + "mvn.sh"
            else -> throw IllegalArgumentException("Could not find executable 'mvn' in mavenBin " + bin().path() + "!")
        }
    }
}