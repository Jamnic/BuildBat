package org.buildbat.core.plugin.maven

import org.buildbat.core.plugin.maven.configuration.MavenConfiguration
import org.buildbat.core.plugin.maven.project.BaseMavenProject
import org.buildbat.core.plugin.maven.project.MavenProject
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.Project
import org.buildbat.core.plugin.xml.XmlFile
import org.buildbat.filesystem.file.File

class MavenPom(
        private val file: XmlFile
) {
    constructor(file: File) : this(XmlFile(file))

    fun modules(configuration: MavenConfiguration): List<MavenProject> {
        return file
                .selector("project")
                .selector("modules")
                .selectMultiple("module")
                .map { BaseMavenProject(BaseProject(it.content(), file.parent().cd(it.content())), configuration.key()) }
    }
}