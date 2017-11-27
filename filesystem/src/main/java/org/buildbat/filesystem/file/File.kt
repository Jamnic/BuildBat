package org.buildbat.filesystem.file

import org.buildbat.filesystem.directory.Directory

interface File : AbstractPathFile {
    fun create(): File
    fun read(): String
    fun path(): String
    fun name(): String
    fun parent(): Directory
}