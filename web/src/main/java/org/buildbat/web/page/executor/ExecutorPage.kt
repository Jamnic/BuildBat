package org.buildbat.web.page.executor

import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.core.task.executor.Executors
import org.buildbat.core.task.executor.LocalExecutor
import org.buildbat.web.page.executor.request.NewExecutorRequest
import org.buildbat.web.page.executor.response.ExecutorInfoResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/executor")
class ExecutorPage {

    private val executors = Executors()
    private val taskPool = TaskPoolProvider.taskPool

    @PutMapping
    fun put(@RequestBody request: NewExecutorRequest) {
        val executor = LocalExecutor(request.name, request.host)
        executors.save(executor)
        taskPool.registerObserver(executor)
    }

    @GetMapping
    fun list(): List<ExecutorInfoResponse> {
        return executors.list().map { ExecutorInfoResponse(it.key(), it.host()) }
    }
}