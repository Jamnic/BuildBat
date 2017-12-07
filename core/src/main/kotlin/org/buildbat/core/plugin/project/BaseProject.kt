package org.buildbat.core.plugin.project

import org.buildbat.core.log.loggable.BaseLoggable
import org.buildbat.core.log.loggable.Loggable
import org.buildbat.core.plugin.project.multimodule.BaseMultiModuleProject
import org.buildbat.core.plugin.project.multimodule.MultiModuleProject
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.BaseJsonEntity
import org.buildbat.json.JsonEntity
import org.buildbat.json.JsonObject

class BaseProject(
        private val name: String,
        private val directory: Directory,
        private val jsonObject: JsonObject = JsonObject(
                "name" to name,
                "directory" to directory.path(),
                "pathName" to name.replace(" ", "_")),
        loggableDelegate: Loggable = BaseLoggable(name),
        jsonEntityDelegate: JsonEntity = BaseJsonEntity(name, jsonObject),
        multiModuleProjectDelegate: MultiModuleProject = BaseMultiModuleProject()
) : Project,
        Loggable by loggableDelegate,
        JsonEntity by jsonEntityDelegate,
        MultiModuleProject by multiModuleProjectDelegate {

    constructor(
            jsonObject: JsonObject
    ) : this(
            jsonObject["name"],
            jsonObject["directory"],
            jsonObject)

    constructor(
            name: String,
            directory: String,
            jsonObject: JsonObject
    ) : this(
            name,
            Directory(directory),
            jsonObject)

    override fun directory(): Directory {
        return this.directory
    }

    override fun json(): JsonObject {
        return this.jsonObject
    }
}