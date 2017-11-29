package org.buildbat.filesystem

import org.buildbat.filesystem.file.BaseFile
import spock.lang.Specification

class FileSpec extends Specification {

    def "should load classpath file"() {
        given:
          BaseFile file = new BaseFile("classpath:server.xml")
        when:
          file.path()
        then:
          notThrown(NullPointerException)
    }
}
