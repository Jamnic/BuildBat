package org.buildbat.web.page.cmd

import org.buildbat.core.plugin.cmd.Cmd
import org.buildbat.core.plugin.cmd.command.CmdShellCommandCreationCommand
import org.buildbat.core.plugin.project.BaseProjects
import org.buildbat.core.task.TaskPoolProvider
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
    private val taskPool = TaskPoolProvider.taskPool

    @PostMapping
    fun command(@RequestBody request: CmdExecutionRequest) {
        val project = projects.find(request.projectName)
        taskPool.add(
                cmd.createTask(
                        CmdShellCommandCreationCommand(
                                request.command,
                                project)
                                .createShellCommand(),
                        project))
    }
}