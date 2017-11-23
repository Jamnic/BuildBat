package org.buildbat.plugin.maven

import org.buildbat.plugin.maven.project.BaseMavenProject
import org.buildbat.plugin.maven.project.EmptyMavenProject
import org.buildbat.plugin.maven.project.MavenProject
import org.buildbat.project.BaseProjects
import org.buildbat.project.Projects

class MavenProjects(
        private val projects: Projects = BaseProjects()
) : Projects by projects {

    override fun find(projectName: String): MavenProject {
        val json = configFile().find(projectName)
        return if (json != null)
            BaseMavenProject(json)
        else
            EmptyMavenProject()
    }

    fun save(project: MavenProject) {
        configFile().save(project.json())
    }
}