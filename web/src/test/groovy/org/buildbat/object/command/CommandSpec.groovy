package org.buildbat.execution.command

import org.buildbat.execution.command.shell.ShellCommand
import org.buildbat.object.project.project.BaseProject
import spock.lang.Specification


class CommandSpec extends Specification {


    def "should replace variables"() {
        when:
          def command = new ShellCommand("some text with {name}", new BaseProject("BuildBat", "Some/Directory"))
        then:
          command.replaceVariables()
    }

}
