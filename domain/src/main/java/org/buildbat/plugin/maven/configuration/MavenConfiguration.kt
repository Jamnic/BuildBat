package org.buildbat.plugin.maven.configuration

import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.Json

interface MavenConfiguration : Json {
    fun bin(): Directory
    fun home(): Directory
    fun mvn(): String
}