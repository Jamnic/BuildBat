package org.buildbat.project

import org.buildbat.filesystem.directory.Directory
import org.buildbat.ConfigFile

interface Projects {
    fun save(project: Project)
    fun find(projectName: String): Project
    fun remove(projectName: String)
    fun directory(): Directory
    fun configFile(): ConfigFile
}