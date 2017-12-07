package org.buildbat.core.log.loggable

import org.buildbat.core.log.LogFile
import org.buildbat.core.log.LogHistory

class BaseLoggable(
        name: String,
        private val logHistory: LogHistory = LogHistory(name)
) : Loggable {

    override fun addLog(log: LogFile) {
        this.logHistory.add(log)
    }

    override fun logs(): Collection<LogFile> {
        return this.logHistory.logs()
    }
}