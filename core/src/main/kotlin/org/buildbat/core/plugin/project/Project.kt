package org.buildbat.core.plugin.project

import org.buildbat.core.log.loggable.Loggable
import org.buildbat.core.plugin.project.multimodule.MultiModuleProject
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonEntity

interface Project : JsonEntity, MultiModuleProject, Loggable {
    fun directory(): Directory
}