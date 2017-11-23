package org.buildbat.web.page.cmd.request

import org.buildbat.web.request.ExecutionRequest

class CmdExecutionRequest(
        command: String = "",
        projectName: String = ""
) : ExecutionRequest(command, projectName)