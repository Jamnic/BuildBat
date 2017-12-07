package org.buildbat.filesystem.file

class ClasspathFile(
        file: File
) : File by file {

    constructor(path: String) : this(BaseFile("classpath:$path"))
}