package org.buildbat.filesystem.file

interface File : AbstractPathFile {
    fun create(): File
    fun read(): String
    fun path(): String
    fun name(): String
}