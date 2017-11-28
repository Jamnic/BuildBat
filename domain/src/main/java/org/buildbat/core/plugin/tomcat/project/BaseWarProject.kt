package org.buildbat.core.plugin.tomcat.project

import org.buildbat.core.file.PomProperties
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.Project
import org.buildbat.core.plugin.tomcat.WarFilePath
import org.buildbat.filesystem.file.File
import org.buildbat.json.JsonObject

class BaseWarProject(
        private val project: Project,
        private val version: String,
        private val artifactName: String,
        private val warFilePath: String = WarFilePath(project, version, artifactName).path(),
        private val jsonObject: JsonObject = project.json().add(
                "version" to version,
                "artifactName" to artifactName,
                "warFile" to warFilePath)
) : WarProject, Project by project {

    private val warFile: File by lazy { WarFilePath(project, version, artifactName) }

    constructor(json: JsonObject) : this(
            BaseProject(json),
            PomProperties(json)["version"],
            PomProperties(json)["artifactId"]
    )

    override fun warFile(): File {
        return warFile
    }

    override fun json(): JsonObject {
        return jsonObject
    }
}