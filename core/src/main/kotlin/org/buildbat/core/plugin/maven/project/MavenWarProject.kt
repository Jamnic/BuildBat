package org.buildbat.core.plugin.maven.project

import org.buildbat.core.file.WarFile
import org.buildbat.core.plugin.tomcat.project.WarProject
import org.buildbat.filesystem.file.File
import org.buildbat.json.JsonObject

class MavenWarProject(
        mavenProjectDelegate: MavenProject,
        private val warFile: File = WarFile(mavenProjectDelegate).find(),
        private val jsonObject: JsonObject = mavenProjectDelegate.json().add("warFile" to warFile.path())
) : WarProject,
        MavenProject by mavenProjectDelegate {

    constructor(
            jsonObject: JsonObject
    ) : this(
            BaseMavenProject(jsonObject))

    override fun warFile(): File {
        return warFile
    }

    override fun json(): JsonObject {
        return jsonObject
    }
}