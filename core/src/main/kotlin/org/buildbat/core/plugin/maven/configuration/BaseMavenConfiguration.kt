package org.buildbat.core.plugin.maven.configuration

import org.buildbat.core.file.ExecutableFile
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.BaseJsonEntity
import org.buildbat.json.JsonEntity
import org.buildbat.json.JsonObject

class BaseMavenConfiguration(
        name: String,
        version: String,
        homePath: String,
        json: JsonEntity = BaseJsonEntity(name, JsonObject(
                "name" to name,
                "version" to version,
                "home" to homePath))
) : MavenConfiguration, JsonEntity by json {

    private val home: Directory by lazy { Directory(homePath) }
    private val bin: Directory by lazy { home.cd("bin") }
    private val mvnExecutable: ExecutableFile by lazy { ExecutableFile("mvn", bin) }

    constructor(
            jsonObject: JsonObject
    ) : this(
            jsonObject["name"],
            jsonObject["version"],
            jsonObject["home"])

    override fun home(): Directory {
        return home
    }

    override fun mvnExecutable(): ExecutableFile {
        return mvnExecutable
    }
}