package org.buildbat.filesystem

import org.buildbat.filesystem.directory.Directory
import java.io.File
import java.util.*
import kotlin.streams.toList

// TODO - should Path be only a string-path carrier or superclass over File and Directory?
abstract class FilesystemObject(
        private val file: File
) {

    constructor(path: String) : this(
            if (path.startsWith("classpath:"))
                File(ClasspathLoader(path).get())
            else
                File(path))

    fun mkdir(): Directory {
        file.mkdir()
        return Directory(path())
    }

    protected fun createNewFile() {
        file.createNewFile()
    }

    fun parent(): Directory {
        return Directory(file.parent)
    }

    open fun path(): String {
        return file.absolutePath
    }

    fun realFile(): File {
        return file
    }

    fun exists(): Boolean {
        return file.exists()
    }

    override fun toString(): String {
        return path()
    }

    fun realFiles(): List<File> {
        val listFiles = file.listFiles()
        return if (listFiles == null)
            listOf()
        else
            Arrays.stream(listFiles).toList()
    }

    fun name(): String {
        return file.name
    }
}