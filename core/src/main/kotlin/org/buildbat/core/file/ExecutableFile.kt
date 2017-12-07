package org.buildbat.core.file

import org.buildbat.filesystem.directory.Directory

class ExecutableFile(
        private val fileName: String,
        private val directory: Directory
) {

    fun executable(): String {
        return when {
            directory.file("$fileName.cmd").exists() -> directory.path() + "$fileName.cmd"
            directory.file("$fileName.bat").exists() -> directory.path() + "$fileName.bat"
            directory.file("$fileName.sh").exists() -> directory.path() + "$fileName.sh"
            else -> throw IllegalArgumentException("Could not find executable '$fileName' in directory ${directory.path()}!")
        }
    }
}