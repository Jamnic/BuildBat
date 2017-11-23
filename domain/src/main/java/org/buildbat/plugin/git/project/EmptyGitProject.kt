package org.buildbat.plugin.git.project

import org.buildbat.project.EmptyProject
import org.buildbat.project.Project

class EmptyGitProject(
        project: EmptyProject = EmptyProject()
) : GitProject, Project by project {
    override fun repository() = throw IllegalStateException("There is no such git project!")
}