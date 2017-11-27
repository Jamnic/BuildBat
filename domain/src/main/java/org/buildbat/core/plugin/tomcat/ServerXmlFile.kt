package org.buildbat.core.plugin.tomcat

import org.buildbat.filesystem.directory.Directory
import org.buildbat.filesystem.file.BaseFile
import org.buildbat.filesystem.file.File

class ServerXmlFile(
        private val name: String,
        private val port: String,
        private val directory: Directory = Directory("serverXml").mkdir(),
        templateFile: File = BaseFile("classpath:server.xml"),
        private val destinationFile: File = directory.file("server-$name-$port.xml")
) : File by destinationFile {

    init {
        templateFile
                .copyTo(destinationFile.create())
                .replace("8080", port)
                .replace("8443", "1" + port)
                .replace("8009", "2" + port)
                .replace("8005", "3" + port)
    }
}