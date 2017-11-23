package org.buildbat.log

import org.buildbat.filesystem.directory.Directory

class LogHistory(
        name: String,
        private val logs: MutableList<Log> = Directory("logs/$name").mkdir().files().map { file -> Log(file) }.toMutableList()
) {

    fun logs(): List<Log> {
        return logs
    }

    fun add(log: Log) {
        logs.add(log)
    }
}