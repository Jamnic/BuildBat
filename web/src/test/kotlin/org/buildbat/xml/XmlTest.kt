package org.buildbat.xml

import org.buildbat.filesystem.file.BaseFile
import org.junit.Test

class XmlTest {

    @Test
    fun should() {
        val file = BaseFile("E:\\Workspace\\Git\\BuildBat\\pom.xml")
        val xmlFile = XmlFile(file)

        val project = xmlFile.selector("project")
        val modules = project.selector("modules")
        val selectors = modules.selectMultiple("module")

        print(modules)
    }

}