package org.buildbat.core.plugin.project

import org.buildbat.core.file.ConfigFile
import org.buildbat.filesystem.directory.Directory

interface Projects {
    fun directory(): Directory
    fun configFile(): ConfigFile
}