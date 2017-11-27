package org.buildbat.core.plugin.git.project

import org.buildbat.core.plugin.project.EmptyProject
import org.buildbat.core.plugin.project.Project

class EmptyGitProject(
        project: EmptyProject = EmptyProject()
) : GitProject, Project by project {
    override fun repository() = throw IllegalStateException("There is no such git project!")
}