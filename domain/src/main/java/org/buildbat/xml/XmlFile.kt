package org.buildbat.xml

import org.buildbat.filesystem.file.File

class XmlFile(
        private val file: File
) : XmlSelector("", "", file.read()), File by file