package org.buildbat.log

import org.buildbat.filesystem.file.File

class Log(
        private val file: File
) {

    fun file() : File {
        return file
    }
}