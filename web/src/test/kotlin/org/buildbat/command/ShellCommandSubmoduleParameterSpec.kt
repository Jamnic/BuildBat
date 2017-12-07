package org.buildbat.command

import org.buildbat.core.execution.command.shell.submodule.ShellCommandSubmoduleParameter
import org.buildbat.core.plugin.maven.project.BaseMavenProject
import org.buildbat.core.plugin.maven.project.MavenProject
import org.buildbat.core.plugin.project.BaseProject
import org.buildbat.filesystem.directory.Directory
import org.buildbat.json.JsonObject
import org.junit.Test

class ShellCommandSubmoduleParameterSpec {

    class MockProject(
            private val project: MavenProject = BaseMavenProject(
                    BaseProject("name", Directory("dir")), "")
    ) : MavenProject by project {
        override fun modules(): List<MavenProject> {
            return listOf(
                    BaseMavenProject(
                            BaseProject("war", Directory("warDir")),
                            "",
                            JsonObject("warFile" to "Some War file")))
        }
    }

    @Test
    fun shouldParseCommand() {
        val shellCommandSubmodule = ShellCommandSubmoduleParameter("[war {warFile}]", MockProject())
        val resolve = shellCommandSubmodule.resolve()
        assert(resolve == "Some War file")
    }
}