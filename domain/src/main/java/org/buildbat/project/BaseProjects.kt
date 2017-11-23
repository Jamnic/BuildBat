package org.buildbat.project

import org.buildbat.filesystem.directory.Directory
import org.buildbat.ConfigFile

class BaseProjects(
        private val directory: Directory = Directory("projects"),
        private val configFile: ConfigFile = ConfigFile("classpath:projects.json")
) : Projects {

    override fun find(projectName: String): Project {
        val json = configFile.find(projectName)
        return if (json != null)
            BaseProject(json)
        else
            EmptyProject()
    }

    override fun save(project: Project) {
        configFile.save(project.json())
    }

    override fun remove(projectName: String) {
        configFile.remove(projectName)
    }

    override fun directory(): Directory {
        return directory
    }

    override fun configFile(): ConfigFile {
        return configFile
    }

    fun list(): List<Project> {
        return configFile.list().map { BaseProject(it) }
    }
}