package org.buildbat.core.plugin.cmd

import org.buildbat.core.execution.command.shell.ShellCommand
import org.buildbat.core.log.LogFactory
import org.buildbat.core.log.LogFile
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.core.plugin.project.SaveableBaseProjects
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonObject
import spock.lang.Specification

class CmdSpec extends Specification {

    def logFactory = Mock(LogFactory)
    def baseProjects = Mock(SaveableBaseProjects)

    def testedCmd = new Cmd(logFactory, baseProjects)

    def anyString = "123 abc"

    def "should add LogFile to project"() {
        given:
          def dummyShellCommand = new ShellCommand(anyString, new Directory(anyString))
          def dummyProject = new BaseProject(anyString, anyString, new JsonObject())
        when:
          testedCmd.createTask(dummyShellCommand, dummyProject)
        then:
          logFactory.new(anyString) >> new LogFile(anyString)
          dummyProject.logs().size() == 1
    }

    def "should create FutureTask"() {
        given:
          def dummyShellCommand = new ShellCommand(anyString, new Directory(anyString))
          def dummyProject = new BaseProject(anyString, anyString, new JsonObject())
        when:
          def task = testedCmd.createTask(dummyShellCommand, dummyProject)
        then:
          logFactory.new(anyString) >> new LogFile(anyString)
          task != null
    }
}