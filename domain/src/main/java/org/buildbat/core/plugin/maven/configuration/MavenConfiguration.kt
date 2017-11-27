package org.buildbat.core.plugin.maven.configuration

import org.buildbat.core.file.ExecutableFile
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonEntity

interface MavenConfiguration : JsonEntity {
    fun home(): Directory
    fun mvnExecutable(): ExecutableFile
}