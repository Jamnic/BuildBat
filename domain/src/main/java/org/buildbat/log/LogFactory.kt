package org.buildbat.log

import org.buildbat.ConfigFile
import org.buildbat.filesystem.directory.Directory
import org.buildbat.filesystem.file.File
import org.buildbat.log.configuration.LogConfiguration
import java.time.LocalDate

class LogFactory(
        private val directory: Directory = Directory("logs"),
        private val logConfigFile: ConfigFile = ConfigFile("classpath:log.config.json")
) {

    fun new(name: String): File {
        val logConfiguration = logConfiguration(name)
        val nextLogConfiguration = logConfiguration.nextId()
        logConfigFile.save(nextLogConfiguration.json())
        return createLogFile(name, nextLogConfiguration.id())
    }

    private fun logConfiguration(name: String): LogConfiguration {
        val json = logConfigFile.find(name)

        return if (json != null)
            LogConfiguration(json)
        else
            LogConfiguration(name)
    }

    private fun createLogFile(name: String, id: Long): File {
        return directory
                .mkdir()
                .cd(name)
                .mkdir()
                .file("${LocalDate.now()} build $id.log")
                .create()
    }
}