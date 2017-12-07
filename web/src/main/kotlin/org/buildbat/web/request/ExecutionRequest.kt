package org.buildbat.web.request

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.buildbat.web.page.cmd.request.CmdExecutionRequest
import org.buildbat.web.page.git.request.GitExecutionRequest
import org.buildbat.web.page.maven.request.MavenExecutionRequest
import org.buildbat.web.page.tomcat.request.TomcatExecutionRequest

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "executor")
@JsonSubTypes(
        Type(name = "maven", value = MavenExecutionRequest::class),
        Type(name = "tomcat", value = TomcatExecutionRequest::class),
        Type(name = "cmd", value = CmdExecutionRequest::class),
        Type(name = "git", value = GitExecutionRequest::class))
open class ExecutionRequest(
        val command: String = "",
        val projectName: String = ""
)