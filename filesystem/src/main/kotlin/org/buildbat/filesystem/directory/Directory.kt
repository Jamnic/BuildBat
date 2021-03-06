package org.buildbat.filesystem.directory

import org.buildbat.filesystem.FilesystemObject
import org.buildbat.filesystem.file.BaseFile
import org.buildbat.filesystem.file.File
import org.buildbat.filesystem.file.MissingFile
import kotlin.text.Regex

class Directory(
        path: String
) : FilesystemObject(path) {

    fun cd(target: String): Directory {
        return Directory("${path()}\\$target")
    }

    fun file(target: String): BaseFile {
        return BaseFile("${path()}\\$target")
    }

    override fun path(): String {
        return "${super.path()}\\"
    }

    fun files(): List<File> {
        return super.realFiles().map { file -> BaseFile(file.absolutePath) }
    }

    fun findByExtension(regexPattern: String): File {
        val file = super.realFiles().find { it.path.endsWith(regexPattern) }
        return if (file == null)
            MissingFile()
        else
            BaseFile(file.absolutePath)
    }
}