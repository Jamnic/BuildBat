package org.buildbat.plugin.tomcat.container

import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonObject

class BaseTomcatContainer(
        private val name: String,
        private val home: Directory
) : TomcatContainer {

    constructor(json: JsonObject) : this(json["name"], json["home"])
    constructor (name: String, homeDirectory: String) : this(name, Directory(homeDirectory))

    override fun name(): String {
        return name
    }

    override fun json(): JsonObject {
        return JsonObject(
                "name" to name,
                "home" to home.path())
    }

    override fun bin(): Directory = home.cd("bin")
    override fun webapps(): Directory = home.cd("webapps")
    override fun conf(): Directory = home.cd("conf")
}