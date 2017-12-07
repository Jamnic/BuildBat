package org.buildbat.web.page.tomcat.request

import org.buildbat.web.request.ExecutionRequest

class TomcatExecutionRequest(
        command: String = "",
        projectName: String = "",
        val submoduleName: String = "",
        val tomcatConfiguration: String = ""
) : ExecutionRequest(command, projectName)