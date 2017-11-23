package org.buildbat.plugin.tomcat.container

import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.Json

interface TomcatContainer : Json {
    fun bin(): Directory
    fun webapps(): Directory
    fun conf(): Directory
}