package org.buildbat.core.plugin.project.repository

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.EmptyProject
import org.buildbat.core.plugin.project.Project
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.json.JsonObject

class BaseProjectsConfigFileRepository(
        configFile: ConfigFile
) : ConfigFileRepository<Project>(configFile) {

    override fun createFromJson(json: JsonObject) = BaseProject(json)
    override fun createFromName(name: String) = EmptyProject()
}