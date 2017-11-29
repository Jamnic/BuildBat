package org.buildbat.object.plugin.maven

import org.buildbat.core.plugin.maven.project.MavenProjects
import org.buildbat.filesystem.file.BaseFile
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonArray
import org.buildbat.core.file.JsonFile
import org.buildbat.object.plugin.maven.config.BaseMavenConfig
import org.buildbat.object.plugin.maven.project.BaseMavenProject
import org.buildbat.object.project.BaseProjects
import org.buildbat.object.project.project.BaseProject
import spock.lang.Specification

class MavenProjectsSpec extends Specification {

    def testJsonFile = new JsonFile(new BaseFile("src/test/resources/test.json"))
    def mavenProjects = new MavenProjects(new BaseProjects(new Directory(""), testJsonFile))

    def cleanup() {
        testJsonFile.write(new JsonArray("name", []))
    }

    def "should find project"() {
        given:
          new MavenConfigurations()
                  .save(new BaseMavenConfig(
                          "Maven",
                          "",
                          ""))
          def mavenProject = new BaseMavenProject(
                  new BaseProject(
                          "Project",
                          new Directory("")),
                  "Maven")
        when:
          mavenProjects.save(mavenProject)
        then:
          mavenProjects.find("Project").name() == "Project"
    }

    def "should not find project"() {
        when:
          mavenProjects.find("Non existent project").mavenConfig()
        then:
          thrown(IllegalStateException)
    }
}
