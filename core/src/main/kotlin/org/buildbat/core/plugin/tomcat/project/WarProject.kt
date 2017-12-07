package org.buildbat.core.plugin.tomcat.project

import org.buildbat.core.plugin.project.Project
import org.buildbat.filesystem.file.File

interface WarProject : Project {
    fun warFile(): File
}