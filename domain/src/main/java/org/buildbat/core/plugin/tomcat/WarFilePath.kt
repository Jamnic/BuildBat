package org.buildbat.core.plugin.tomcat

import org.buildbat.core.plugin.project.Project
import org.buildbat.filesystem.file.BaseFile
import org.buildbat.filesystem.file.File

class WarFilePath(
        project: Project,
        version: String,
        artifactId: String,
        private val file: File = BaseFile("${project.directory().path()}target\\$artifactId-$version.war")
) : File by file