package org.buildbat.core.plugin.tomcat.project

import org.buildbat.core.file.WarFile
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.Project
import org.buildbat.filesystem.file.File
import org.buildbat.json.JsonObject

class BaseWarProject(
        projectDelegate: Project,
        private val warFile: File = WarFile(projectDelegate).find(),
        private val jsonObject: JsonObject = projectDelegate.json().add("warFile" to warFile.path())
) : WarProject,
        Project by projectDelegate {

    constructor(
            json: JsonObject
    ) : this(
            BaseProject(json))

    override fun warFile(): File {
        return warFile
    }

    override fun json(): JsonObject {
        return jsonObject
    }
}