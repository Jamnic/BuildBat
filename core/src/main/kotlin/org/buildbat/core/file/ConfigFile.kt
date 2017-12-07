package org.buildbat.core.file

import org.buildbat.filesystem.file.BaseFile
import org.buildbat.json.JsonObject

class ConfigFile(
        private val file: JsonFile
) : JsonFile by file {
    constructor(configurationPath: String) : this(BaseJsonFile("name", BaseFile(configurationPath)))

    fun find(key: String): JsonObject? {
        return file.jsonArray().find(key)
    }

    fun save(json: JsonObject) {
        file.write(file.jsonArray().save(json))
    }

    fun remove(projectName: String) {
        file.write(file.jsonArray().remove(projectName))
    }

    fun list(): List<JsonObject> {
        return file.jsonArray().list()
    }
}