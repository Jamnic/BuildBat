package org.buildbat.core.plugin.project

import org.buildbat.core.future.Future
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonEntity
import org.buildbat.core.log.LogFile

interface Project : JsonEntity {
    fun directory(): Directory
    fun addLog(log: LogFile)
    fun logs(): Collection<LogFile>
    fun cmd(command: String): Future<Project>
}