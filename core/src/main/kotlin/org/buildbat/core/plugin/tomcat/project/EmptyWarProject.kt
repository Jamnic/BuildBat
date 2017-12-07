package org.buildbat.core.plugin.tomcat.project

import org.buildbat.core.plugin.project.EmptyProject
import org.buildbat.core.plugin.project.Project

class EmptyWarProject(
        private val project: Project = EmptyProject()
) : WarProject, Project by project {
    override fun warFile() = throw IllegalStateException("There is no such project!")
}