package org.buildbat.core.plugin.maven.project.repository

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.plugin.maven.project.BaseMavenProject
import org.buildbat.core.plugin.maven.project.EmptyMavenProject
import org.buildbat.core.plugin.maven.project.MavenProject
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.json.JsonObject

class MavenProjectsConfigFileRepository(
        configFile: ConfigFile
) : ConfigFileRepository<MavenProject>(configFile) {

    override fun createFromJson(json: JsonObject) = BaseMavenProject(json)
    override fun createFromName(name: String) = EmptyMavenProject()
}