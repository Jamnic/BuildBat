package org.buildbat.plugin.maven.project

import org.buildbat.json.JsonObject
import org.buildbat.plugin.maven.MavenConfigurations
import org.buildbat.plugin.maven.MavenPom
import org.buildbat.plugin.maven.configuration.MavenConfiguration
import org.buildbat.project.BaseProject
import org.buildbat.project.Project

class BaseMavenProject(
        private val project: Project,
        private val mavenConfiguration: MavenConfiguration
) : MavenProject, Project by project {

    constructor(
            json: JsonObject
    ) : this(BaseProject(json), MavenConfigurations().find(json["maven"]))

    override fun mavenConfig(): MavenConfiguration {
        return mavenConfiguration
    }

    override fun modules(): List<Project> {
        return MavenPom(directory().file("pom.xml"))
                .modules(mavenConfiguration)
    }

    override fun json(): JsonObject {
        return project
                .json()
                .add(
                        "maven" to mavenConfiguration.name(),
                        "modules" to modules()
                                .map { it.name() }
                                .fold("[", { acc, name -> "$acc, {\"name\": \"$name\"}" }) + "]")
    }
}