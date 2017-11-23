package org.buildbat.filesystem.file

import org.buildbat.filesystem.FilesystemObject
import java.io.FileWriter

class WritableFile(
        file: File
) : File by file {

    constructor(path : String) : this(BaseFile(path))

    fun write(string: String) : WritableFile {
        val writer = FileWriter(realFile())
        writer.write(string)
        writer.close()
        return this
    }

    fun replace(old: String, new: String): WritableFile {
        write(read().replace(old, new))
        return this
    }

    fun copyTo(destinationPath: FilesystemObject): WritableFile {
        return WritableFile(destinationPath.path()).write(read())
    }

    fun append(string: String) {
        val writer = FileWriter(realFile(), true)
        writer.append("$string${System.lineSeparator()}")
        writer.close()
    }
}