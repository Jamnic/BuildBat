package org.buildbat.plugin.war.project

import org.buildbat.filesystem.file.BaseFile
import org.buildbat.json.JsonObject
import org.buildbat.project.BaseProject
import org.buildbat.project.Project
import org.buildbat.project.PropertiesLoader

class BaseWarProject(
        private val project: Project,
        private val version: String,
        private val artifactName: String,
        private val warFile: String = BaseFile(WarFilePath(project, version, artifactName).get()).path()
) : WarProject, Project by project {

    constructor(json: JsonObject) : this(
            BaseProject(json),
            PropertiesLoader(json)["version"],
            PropertiesLoader(json)["artifactId"]
    )

    override fun warFile(): BaseFile {
        return BaseFile(WarFilePath(this, version, artifactName).get())
    }

    override fun json(): JsonObject {
        return project.json().add(
                "version" to version,
                "artifactName" to artifactName,
                "warFile" to warFile)
    }
}