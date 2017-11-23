package org.buildbat.filesystem.file

import java.io.File

interface AbstractPathFile {
    fun realFile() : File
}