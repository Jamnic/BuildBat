package org.buildbat.core.plugin.git.project

import org.buildbat.core.plugin.git.project.repository.GitProjectsConfigFileRepository
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.core.plugin.project.Projects
import org.buildbat.core.repository.Repository

class GitProjects(
        private val projects: Projects = BaseProjects(),
        private val repository: Repository<GitProject, String> = GitProjectsConfigFileRepository(projects.configFile())
) : Repository<GitProject, String> by repository, Projects by projects