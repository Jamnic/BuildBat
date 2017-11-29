package org.buildbat.object.plugin.maven

import org.buildbat.object.plugin.maven.config.BaseMavenConfig
import org.buildbat.object.plugin.maven.project.BaseMavenProject
import org.buildbat.object.plugin.maven.project.MavenProject
import org.buildbat.object.project.project.Project
import spock.lang.Specification

class MavenSpec extends Specification {

    def maven = new Maven()

    def "should create maven action"() {
        given:
          def anyCommand = "command"
          def anyProject = Mock(MavenProject)
        when:
          def action = maven.custom(anyCommand, anyProject)
        then:
          action.future != null
          action.logFile != null
    }

    def "should resolve maven action"() {
        given:
          def action = maven.custom("command", new BaseMavenProject(Mock(Project), new BaseMavenConfig("", "", "")))
        when:
          def logfile = action.resolve()
        then:
          logfile != null
    }
}
