package org.buildbat.core.plugin.tomcat.container

import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.BaseJsonEntity
import org.buildbat.json.JsonEntity
import org.buildbat.json.JsonObject

class BaseTomcatContainer(
        name: String,
        homePath: String,
        jsonObject: JsonObject = JsonObject(
                "name" to name,
                "home" to homePath),
        json: JsonEntity = BaseJsonEntity(name, jsonObject)
) : TomcatContainer, JsonEntity by json {

    private val home: Directory by lazy { Directory(homePath) }

    constructor(
            jsonObject: JsonObject
    ) : this(
            jsonObject["name"],
            jsonObject["home"],
            jsonObject)

    override fun home(): Directory {
        return home
    }
}