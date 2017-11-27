package org.buildbat.core.future

import org.buildbat.core.log.LogFile

class FutureResult(
        val name: String = "",
        val logFile: String = ""
) {
    constructor(logFile: LogFile) : this(logFile.name(), logFile.path())
}