package org.buildbat.core.plugin.project

import org.buildbat.filesystem.directory.Directory
import org.buildbat.core.file.ConfigFile
import org.buildbat.core.plugin.project.repository.BaseProjectsConfigFileRepository
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.core.repository.Repository
import org.buildbat.json.JsonObject

class BaseProjects(
        private val directory: Directory = Directory("projects"),
        private val configFile : ConfigFile = ConfigFile("classpath:projects.json"),
        private val repository: Repository<Project, String> = BaseProjectsConfigFileRepository(configFile)
) : Repository<Project, String> by repository, Projects {

    override fun configFile(): ConfigFile {
        return configFile
    }

    override fun directory(): Directory {
        return directory
    }
}