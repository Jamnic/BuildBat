package org.buildbat.object.plugin.maven

import org.buildbat.filesystem.file.BaseFile
import org.buildbat.json.JsonArray
import org.buildbat.JsonFile
import org.buildbat.object.plugin.maven.config.BaseMavenConfig
import spock.lang.Specification

class MavenConfigurationsSpec extends Specification {

    def testJsonFile = new JsonFile(new BaseFile("src/test/resources/test.json"))
    def mavenProjects = new MavenConfigurations("name", testJsonFile)

    def cleanup() {
        testJsonFile.write(new JsonArray("name", []))
    }

    def "should save maven config"() {
        given:
          def mavenConfig = new BaseMavenConfig("Maven", "", "")
        when:
          mavenProjects.save(mavenConfig)
        then:
          mavenProjects.find("Maven").name() == "Maven"
    }

    def "should not find maven config"() {
        when:
          mavenProjects.find("Non existent maven config").bin()
        then:
          thrown(IllegalStateException)
    }
}
