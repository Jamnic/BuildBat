package org.buildbat.core.plugin.maven.project

import org.buildbat.core.plugin.project.EmptyProject
import org.buildbat.core.plugin.project.Project

class EmptyMavenProject(
        project: EmptyProject = EmptyProject()
) : MavenProject, Project by project {
    override fun mavenConfiguration() = throw IllegalStateException("There is no such maven project!")
    override fun modules() = throw IllegalStateException("There is no such maven project!")
}