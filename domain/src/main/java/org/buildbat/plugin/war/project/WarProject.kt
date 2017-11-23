package org.buildbat.plugin.war.project

import org.buildbat.filesystem.file.BaseFile
import org.buildbat.project.Project

interface WarProject : Project {
    fun warFile(): BaseFile
}