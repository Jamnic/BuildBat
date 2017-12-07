package org.buildbat.core.log.loggable

import org.buildbat.core.log.LogFile

interface Loggable {
    fun addLog(log: LogFile)
    fun logs(): Collection<LogFile>
}