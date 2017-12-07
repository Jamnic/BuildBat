package org.buildbat.web.page.project.response

import org.buildbat.core.log.LogFile

class LogInfoResponse(
        val name: String = "",
        val logFile: String = ""
) {
    constructor(logFile: LogFile) : this(logFile.name(), logFile.path())
}