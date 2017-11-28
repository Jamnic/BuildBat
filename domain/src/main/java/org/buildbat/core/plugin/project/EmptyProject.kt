package org.buildbat.core.plugin.project

import org.buildbat.core.log.LogFile
import org.buildbat.json.EmptyJsonEntity
import org.buildbat.json.JsonEntity

class EmptyProject(
        private val json: JsonEntity = EmptyJsonEntity()
) : Project, JsonEntity by json {
    override fun modules() = throw IllegalStateException("There is no such project!")
    override fun addLog(log: LogFile) = throw IllegalStateException("There is no such project!")
    override fun logs() = throw IllegalStateException("There is no such project!")
    override fun cmd(command: String) = throw IllegalStateException("There is no such project!")
    override fun directory() = throw IllegalStateException("There is no such project!")
}