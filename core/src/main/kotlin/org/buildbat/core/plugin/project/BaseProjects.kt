package org.buildbat.core.plugin.project

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.plugin.project.repository.BaseProjectsConfigFileRepository
import org.buildbat.core.repository.Repository
import org.buildbat.filesystem.directory.Directory

// TODO bad design
interface SaveableBaseProjects : Repository<Project, String>

class BaseProjects(
        private val directory: Directory = Directory("projects"),
        private val configFile: ConfigFile = ConfigFile("classpath:projects.json"),
        private val repository: Repository<Project, String> = BaseProjectsConfigFileRepository(configFile)
) : Repository<Project, String> by repository, SaveableBaseProjects, Projects {

    override fun configFile(): ConfigFile {
        return configFile
    }

    override fun directory(): Directory {
        return directory
    }
}