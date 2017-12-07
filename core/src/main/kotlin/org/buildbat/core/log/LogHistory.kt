package org.buildbat.core.log

import org.buildbat.filesystem.directory.Directory

// TODO bad design
class LogHistory(
        name: String,
        private val logs: MutableList<LogFile> = Directory("logs/$name").mkdir().files().map { file -> LogFile(file) }.toMutableList()
) {

    fun logs(): List<LogFile> {
        return logs
    }

    fun add(log: LogFile) {
        logs.add(log)
    }
}