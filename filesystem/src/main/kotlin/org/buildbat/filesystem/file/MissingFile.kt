package org.buildbat.filesystem.file

class MissingFile : File {
    override fun create() = throw IllegalStateException("There is no such file!")
    override fun read() = throw IllegalStateException("There is no such file!")
    override fun path() = throw IllegalStateException("There is no such file!")
    override fun name() = throw IllegalStateException("There is no such file!")
    override fun copyTo(destinationPath: File) = throw IllegalStateException("There is no such file!")
    override fun parent() = throw IllegalStateException("There is no such file!")
    override fun realFile() = throw IllegalStateException("There is no such file!")
}