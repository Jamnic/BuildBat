package org.buildbat.core.task.executor.repository

import org.buildbat.core.file.ConfigFile
import org.buildbat.core.repository.ConfigFileRepository
import org.buildbat.core.task.executor.Executor
import org.buildbat.core.task.executor.LocalExecutor
import org.buildbat.core.task.executor.MissingExecutor
import org.buildbat.json.JsonObject

class ExecutorsConfigFileRepository(
        configFile: ConfigFile
) : ConfigFileRepository<Executor>(configFile) {
    constructor(configFilePath: String) : this(ConfigFile(configFilePath))

    override fun createFromJson(json: JsonObject) = LocalExecutor(json)
    override fun createFromName(name: String) = MissingExecutor()
}
