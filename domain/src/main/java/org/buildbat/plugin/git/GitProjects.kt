package org.buildbat.plugin.git

import org.buildbat.plugin.git.project.BaseGitProject
import org.buildbat.plugin.git.project.EmptyGitProject
import org.buildbat.plugin.git.project.GitProject
import org.buildbat.project.Projects
import org.buildbat.project.BaseProjects

class GitProjects(
        private val projects: Projects = BaseProjects()
) : Projects by projects {

    override fun find(projectName: String): GitProject {
        val json = configFile().find(projectName)
        return if (json != null)
            BaseGitProject(json)
        else
            EmptyGitProject()
    }

    fun save(project: GitProject) {
        configFile().save(project.json())
    }
}