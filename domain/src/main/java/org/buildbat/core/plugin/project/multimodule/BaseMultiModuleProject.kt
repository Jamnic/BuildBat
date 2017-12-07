package org.buildbat.core.plugin.project.multimodule

import org.buildbat.core.plugin.project.EmptyProject
import org.buildbat.core.plugin.project.Project

class BaseMultiModuleProject(
        private val modules: MutableMap<String, Project> = mutableMapOf()
) : MultiModuleProject {

    override fun modules(): Collection<Project> {
        return modules.values
    }

    override fun module(moduleName: String): Project {
        return modules[moduleName] ?: EmptyProject()
    }

    override fun addModule(module: Project) {
        modules.put(module.key(), module)
    }
}