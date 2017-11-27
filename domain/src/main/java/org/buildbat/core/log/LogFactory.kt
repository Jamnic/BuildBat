package org.buildbat.core.log

import org.buildbat.core.log.configuration.LogConfigurations
import org.buildbat.filesystem.directory.Directory
import org.buildbat.filesystem.file.File
import java.time.LocalDate

class LogFactory(
        private val directory: Directory = Directory("logs"),
        private val logConfigurations: LogConfigurations = LogConfigurations()
) {

    fun new(name: String): LogFile {
        val nextLogConfiguration = logConfigurations.find(name).nextId()
        logConfigurations.save(nextLogConfiguration)
        return createLogFile(name, nextLogConfiguration.id())
    }

    private fun createLogFile(name: String, id: Long): LogFile {
        return LogFile(directory.cd(name).file("${LocalDate.now()} build $id.log").create())
    }
}