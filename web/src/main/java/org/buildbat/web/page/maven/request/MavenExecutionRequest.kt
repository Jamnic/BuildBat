package org.buildbat.web.page.maven.request

import org.buildbat.web.request.ExecutionRequest

class MavenExecutionRequest(
        command: String = "",
        projectName: String = ""
) : ExecutionRequest(command, projectName)