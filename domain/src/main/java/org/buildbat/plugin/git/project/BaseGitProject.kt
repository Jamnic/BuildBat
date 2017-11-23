package org.buildbat.plugin.git.project

import org.buildbat.json.JsonObject
import org.buildbat.project.BaseProject
import org.buildbat.project.Project

class BaseGitProject(
        private val project: Project,
        private val repository: String
) : GitProject, Project by project {

    constructor(
            json: JsonObject
    ) : this(BaseProject(json), json["repository"])

    override fun repository(): String {
        return repository
    }

    override fun json(): JsonObject {
        return project.json()
                .add("repository" to repository)
    }
}