package org.buildbat.core.repository

import org.buildbat.core.file.ConfigFile
import org.buildbat.json.JsonEntity
import org.buildbat.json.JsonObject


abstract class ConfigFileRepository<T : JsonEntity>(
        private val configFile: ConfigFile
) : Repository<T, String> {

    constructor(filePath: String) : this(ConfigFile(filePath))

    abstract fun createFromJson(json: JsonObject): T
    abstract fun createFromName(name: String): T

    override fun find(name: String): T {
        val json = configFile.find(name)
        return if (json != null) createFromJson(json) else createFromName(name)
    }

    override fun save(t: T): T {
        configFile.save(t.json())
        return t
    }

    override fun list(): List<T> {
        return configFile.list().map { createFromJson(it) }
    }

    override fun remove(name: String) {
        configFile.remove(name)
    }
}