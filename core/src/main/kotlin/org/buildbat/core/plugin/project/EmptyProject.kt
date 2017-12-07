package org.buildbat.core.plugin.project

import org.buildbat.core.log.LogFile
import org.buildbat.core.log.loggable.EmptyLoggable
import org.buildbat.core.log.loggable.Loggable
import org.buildbat.core.plugin.project.multimodule.EmptyMultiModuleProject
import org.buildbat.core.plugin.project.multimodule.MultiModuleProject
import org.buildbat.json.EmptyJsonEntity
import org.buildbat.json.JsonEntity

class EmptyProject(
        loggableDelegate: Loggable = EmptyLoggable(),
        jsonEntityDelegate: JsonEntity = EmptyJsonEntity(),
        multiModuleProjectDelegate: MultiModuleProject = EmptyMultiModuleProject()
) : Project,
        Loggable by loggableDelegate,
        JsonEntity by jsonEntityDelegate,
        MultiModuleProject by multiModuleProjectDelegate {

    override fun addLog(log: LogFile) = throw IllegalStateException("There is no such project!")
    override fun logs() = throw IllegalStateException("There is no such project!")
    override fun directory() = throw IllegalStateException("There is no such project!")
}