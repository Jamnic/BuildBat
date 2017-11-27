package org.buildbat.web.page.cmd

import org.buildbat.core.future.FutureResult
import org.buildbat.core.plugin.cmd.Cmd
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.web.page.cmd.request.CmdExecutionRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cmd")
class CmdPage {

    private val cmd = Cmd()
    private val projects = BaseProjects()

    @PostMapping
    fun command(
            @RequestBody request: CmdExecutionRequest
    ): FutureResult {
        return cmd
                .execute(request.command, projects.find(request.projectName))
                .resolve({ future -> projects.save(future) })
    }

}