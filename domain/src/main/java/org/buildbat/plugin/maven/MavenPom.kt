package org.buildbat.plugin.maven

import org.buildbat.filesystem.file.File
import org.buildbat.plugin.maven.configuration.MavenConfiguration
import org.buildbat.plugin.maven.project.BaseMavenProject
import org.buildbat.project.BaseProject
import org.buildbat.project.Project
import org.buildbat.xml.XmlFile

class MavenPom(
        private val file: XmlFile
) {
    constructor(file: File) : this(XmlFile(file))

    fun modules(configuration: MavenConfiguration): List<Project> {
        return file
                .selector("project")
                .selector("modules")
                .selectMultiple("module")
                .map { BaseMavenProject(BaseProject(it.content(), file.parent().cd(it.content())), configuration) }
    }
}