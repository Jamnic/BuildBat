package org.buildbat.plugin.war

import org.buildbat.plugin.war.project.BaseWarProject
import org.buildbat.plugin.war.project.EmptyWarProject
import org.buildbat.plugin.war.project.WarProject
import org.buildbat.project.BaseProjects
import org.buildbat.project.Projects

class WarProjects(
        private val projects: Projects = BaseProjects()
) : Projects by projects {

    override fun find(projectName: String): WarProject {
        val json = configFile().find(projectName)
        return if (json != null)
            BaseWarProject(json)
        else
            EmptyWarProject()
    }

    fun save(project: WarProject) {
        configFile().save(project.json())
    }
}