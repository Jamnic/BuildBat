package org.buildbat.core.plugin.project

import org.buildbat.core.future.FutureTask
import org.buildbat.core.log.LogFile
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonEntity

interface Project : JsonEntity {
    fun directory(): Directory
    fun addLog(log: LogFile)
    fun logs(): Collection<LogFile>
    fun cmd(command: String): FutureTask<Project>
    fun modules(): List<Project>
}