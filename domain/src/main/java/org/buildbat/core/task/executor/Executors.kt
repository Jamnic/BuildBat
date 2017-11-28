package org.buildbat.core.task.executor

import org.buildbat.core.repository.Repository
import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.core.task.executor.repository.ExecutorsConfigFileRepository

class Executors(
        private val repository: Repository<Executor, String> = ExecutorsConfigFileRepository("classpath:executors.config.json")
) : Repository<Executor, String> by repository {

    // TODO find a better way to init executors
    init {
        list().forEach { TaskPoolProvider.INSTANCE.taskPool.registerObserver(it) }
    }
}