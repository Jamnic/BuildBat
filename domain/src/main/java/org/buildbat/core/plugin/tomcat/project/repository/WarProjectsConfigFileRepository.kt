package org.buildbat.core.plugin.tomcat.project.repository

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.plugin.maven.project.MavenWarProject
import org.buildbat.core.plugin.tomcat.project.BaseWarProject
import org.buildbat.core.plugin.tomcat.project.EmptyWarProject
import org.buildbat.core.plugin.tomcat.project.WarProject
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.json.JsonObject

class WarProjectsConfigFileRepository(
        configFile: ConfigFile
) : ConfigFileRepository<WarProject>(configFile) {

    override fun createFromJson(json: JsonObject): WarProject {
        return if (json["maven"].isNotEmpty())
            MavenWarProject(json)
        else
            BaseWarProject(json)
    }

    override fun createFromName(name: String) = EmptyWarProject()
}