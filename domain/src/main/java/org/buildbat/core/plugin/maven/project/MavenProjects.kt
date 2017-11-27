package org.buildbat.core.plugin.maven.project

import org.buildbat.core.plugin.maven.project.repository.MavenProjectsConfigFileRepository
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.core.plugin.project.Projects
import org.buildbat.core.repository.Repository

class MavenProjects(
        private val projects: Projects = BaseProjects(),
        private val repository: Repository<MavenProject, String> = MavenProjectsConfigFileRepository(projects.configFile())
) : Repository<MavenProject, String> by repository,
        Projects by projects