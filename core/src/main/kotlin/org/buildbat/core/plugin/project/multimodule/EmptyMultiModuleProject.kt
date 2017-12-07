package org.buildbat.core.plugin.project.multimodule

import org.buildbat.core.plugin.project.Project

class EmptyMultiModuleProject : MultiModuleProject {
    override fun modules() = throw IllegalStateException("There is no such MultiModuleProject!")
    override fun module(moduleName: String) = throw IllegalStateException("There is no such MultiModuleProject!")
    override fun addModule(module: Project) = throw IllegalStateException("There is no such MultiModuleProject!")
}