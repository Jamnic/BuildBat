package org.buildbat.filesystem.file

import org.buildbat.filesystem.FilesystemObject
import java.io.FileReader

class BaseFile(
        path: String
) : FilesystemObject(path), File {

    override fun create(): BaseFile {
        super.createNewFile()
        return this
    }

    override fun read(): String {
        val reader = FileReader(realFile())
        return reader.readText()
    }

    override fun copyTo(destinationPath: File): WritableFile {
        return WritableFile(destinationPath.path()).write(read())
    }
}