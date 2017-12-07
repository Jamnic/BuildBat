package org.buildbat.core.plugin.git.project

import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.Project
import org.buildbat.json.JsonObject

class BaseGitProject(
        projectDelegate: Project,
        private val repository: String,
        private val jsonObject: JsonObject = projectDelegate.json().add("repository" to repository)
) : GitProject,
        Project by projectDelegate {

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