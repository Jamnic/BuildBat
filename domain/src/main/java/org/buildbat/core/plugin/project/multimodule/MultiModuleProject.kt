package org.buildbat.core.plugin.project.multimodule

import org.buildbat.core.plugin.project.Project

interface MultiModuleProject {
    fun modules(): Collection<Project>
    fun module(moduleName: String): Project
    fun addModule(module: Project)
}