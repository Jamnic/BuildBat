package org.buildbat.core.file

import org.buildbat.core.plugin.project.Project
import org.buildbat.filesystem.file.File

class WarFile(
        private val project: Project
) {

    fun find(): File {
        return project.directory().cd("target").findByExtension(".war")
    }
}