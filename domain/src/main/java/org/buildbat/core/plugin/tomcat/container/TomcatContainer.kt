package org.buildbat.core.plugin.tomcat.container

import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonEntity

interface TomcatContainer : JsonEntity {
    fun home(): Directory
}