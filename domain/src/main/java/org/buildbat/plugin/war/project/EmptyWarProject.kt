package org.buildbat.plugin.war.project

import org.buildbat.project.EmptyProject
import org.buildbat.project.Project

class EmptyWarProject(
        private val project: Project = EmptyProject()
) : WarProject, Project by project {
    override fun warFile() = throw IllegalStateException("There is no such project!")
}