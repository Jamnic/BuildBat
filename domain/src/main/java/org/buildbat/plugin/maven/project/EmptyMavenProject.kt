package org.buildbat.plugin.maven.project

import org.buildbat.project.EmptyProject
import org.buildbat.project.Project

class EmptyMavenProject(
        project: EmptyProject = EmptyProject()
) : MavenProject, Project by project {
    override fun mavenConfig() = throw IllegalStateException("There is no such maven project!")
}