package org.buildbat.object

import org.buildbat.filesystem.file.BaseFile
import spock.lang.Specification

class FutureSpec extends Specification {

    def "should map Future"() {
        when:
          def future = new LoggedFuture(new BaseFile("test"), new Future({ println("First") }))
                  .map(new LoggedFuture(new BaseFile("test"), new Future({ println("Second") })))
                  .map(new LoggedFuture(new BaseFile("test"), new Future({ println("Third") })))
        then:
          future.resolve()
    }

    def "should map Future function"() {
        when:
          def future = new Future({ println("First") })
                  .map({ println("Second") })
                  .map({ println("Third") })
        then:
          future.resolve()
    }
}