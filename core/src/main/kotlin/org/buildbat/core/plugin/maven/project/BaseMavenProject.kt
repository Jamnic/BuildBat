package org.buildbat.core.plugin.maven.project

import org.buildbat.core.plugin.maven.MavenPom
import org.buildbat.core.plugin.maven.configuration.MavenConfiguration
import org.buildbat.core.plugin.maven.configuration.MavenConfigurations
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.Project
import org.buildbat.json.JsonObject

class BaseMavenProject(
        projectDelegate: Project,
        mavenConfigurationName: String,
        private val jsonObject: JsonObject = projectDelegate.json().add(
                "maven" to mavenConfigurationName)
) : MavenProject,
        Project by projectDelegate {

    private val mavenConfiguration: MavenConfiguration by lazy {
        MavenConfigurations().find(mavenConfigurationName)
    }

    init {
        MavenPom(directory().file("pom.xml"))
                .modules(mavenConfiguration).forEach { addModule(it) }
    }

    constructor(
            project: Project
    ) : this(
            project.json())

    constructor(
            jsonObject: JsonObject
    ) : this(
            BaseProject(jsonObject),
            jsonObject["maven"],
            jsonObject)

    override fun mavenConfiguration(): MavenConfiguration {
        return mavenConfiguration
    }

    override fun json(): JsonObject {
        return jsonObject
                .add("modules" to modules()
                        .map { it.key() }
                        .fold("[", { acc, name -> "$acc $name," }) + "]")

    }
}