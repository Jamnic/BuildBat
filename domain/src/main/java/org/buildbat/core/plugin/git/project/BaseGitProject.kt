package org.buildbat.core.plugin.git.project

import org.buildbat.json.JsonObject
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.Project

class BaseGitProject(
        project: Project,
        private val repository: String,
        private val jsonObject: JsonObject = project.json().add(
                "repository" to repository)
) : GitProject, Project by project {

    constructor(
            jsonObject: JsonObject
    ) : this(
            BaseProject(jsonObject),
            jsonObject["repository"],
            jsonObject)

    override fun repository(): String {
        return repository
    }

    override fun json(): JsonObject {
        return jsonObject
    }
}