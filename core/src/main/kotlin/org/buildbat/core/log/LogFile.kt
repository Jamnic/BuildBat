package org.buildbat.core.log

import org.buildbat.filesystem.file.BaseFile
import org.buildbat.filesystem.file.File

class LogFile(
        private val file: File
) : File by file {
    constructor(fileName: String) : this(BaseFile(fileName))
}