package org.buildbat.core.plugin.git.project.repository

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.plugin.git.project.BaseGitProject
import org.buildbat.core.plugin.git.project.EmptyGitProject
import org.buildbat.core.plugin.git.project.GitProject
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.json.JsonObject

class GitProjectsConfigFileRepository(
        configFile: ConfigFile
) : ConfigFileRepository<GitProject>(configFile) {

    override fun createFromJson(json: JsonObject) = BaseGitProject(json)
    override fun createFromName(name: String) = EmptyGitProject()
}