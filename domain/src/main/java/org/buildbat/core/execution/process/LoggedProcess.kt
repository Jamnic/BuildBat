package org.buildbat.core.execution.process

import org.buildbat.filesystem.file.File
import org.buildbat.filesystem.file.WritableFile
import java.time.LocalDateTime

class LoggedProcess(
        private val logFile: WritableFile,
        private val process: Process
) : Process by process {

    constructor(file: File, process: Process) : this(WritableFile(file), process)

    init {
        while (process.alive()) {
            process.readInputStream().reader().forEachLine {
                logFile.append("${LocalDateTime.now()}: $it")
            }
        }
    }
}