package org.buildbat.web.page.git.request

import org.buildbat.web.request.ExecutionRequest

class GitExecutionRequest(
        command: String = "",
        projectName: String = ""
) : ExecutionRequest(command, projectName)