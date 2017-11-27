package org.buildbat.project

import org.buildbat.Future
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.Json
import org.buildbat.log.Log

interface Project : Json {
    fun directory(): Directory
    fun addLog(log: Log)
    fun logs(): Collection<Log>
    fun cmd(command: String): Future<Project>
    fun modules(): List<Project>
}