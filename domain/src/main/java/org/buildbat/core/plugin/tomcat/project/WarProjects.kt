package org.buildbat.core.plugin.tomcat.project

import org.buildbat.core.plugin.tomcat.project.repository.WarProjectsConfigFileRepository
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.core.plugin.project.Projects
import org.buildbat.core.repository.Repository

class WarProjects(
        private val projects: Projects = BaseProjects(),
        private val repository: Repository<WarProject, String> = WarProjectsConfigFileRepository(projects.configFile())
) : Repository<WarProject, String> by repository, Projects by projects