package org.buildbat.object.plugin.tomcat

import spock.lang.Specification

class TomcatContainerSpec extends Specification {

    def "should run"() {
        given:
          def container = new TomcatContainers().find("Tomcat 8.0")
        when:
          container.run("10000")
        then:
          container
    }

}
