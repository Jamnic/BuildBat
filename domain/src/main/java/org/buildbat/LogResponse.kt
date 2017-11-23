package org.buildbat

import org.buildbat.filesystem.file.File

class LogResponse(
        val name: String = "",
        val logFile: String = ""
) {
    constructor(file: File) : this(file.name(), file.path())
}