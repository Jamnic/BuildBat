package org.buildbat.project

import org.buildbat.json.EmptyJson
import org.buildbat.json.Json
import org.buildbat.log.Log

class EmptyProject(
        private val json: Json = EmptyJson()
) : Project, Json by json {
    override fun modules() = throw IllegalStateException("There is no such project!")
    override fun addLog(log: Log) = throw IllegalStateException("There is no such project!")
    override fun logs() = throw IllegalStateException("There is no such project!")
    override fun cmd(command: String) = throw IllegalStateException("There is no such project!")
    override fun directory() = throw IllegalStateException("There is no such project!")
}