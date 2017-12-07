package org.buildbat.core.log.loggable

import org.buildbat.core.log.LogFile

class EmptyLoggable : Loggable {
    override fun addLog(log: LogFile) = throw IllegalStateException("There is no such loggable!")
    override fun logs(): Collection<LogFile> = throw IllegalStateException("There is no such loggable!")
}