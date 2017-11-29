package org.buildbat.`object`

import org.buildbat.core.future.BaseFuture
import org.buildbat.core.future.Future
import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.core.task.executor.LocalExecutor
import org.junit.Test

class ExecutorPoolTest {

    @Test
    fun shouldTest() {

        val taskPool = TaskPoolProvider.INSTANCE.taskPool

        val executors = listOf(LocalExecutor("Ann", "", "500"),
                LocalExecutor("Bob", "", "1000"),
                LocalExecutor("Cynthia", "", "1200"),
                LocalExecutor("Daniel", "", "800"),
                LocalExecutor("Frodo", "", "200"),
                LocalExecutor("Greta", "", "400"),
                LocalExecutor("Hilda", "", "400"),
                LocalExecutor("Ian", "", "400"),
                LocalExecutor("Jan", "", "1000"))

        executors.forEach { taskPool.observe(it) }

        val millis = System.currentTimeMillis()

        for (i in 0..19) {
            taskPool.add(BaseFuture({ println(i) }))
        }

        while (taskPool.isNotEmpty() || executors.any { !it.isFree() }) {

        }

        println(System.currentTimeMillis() - millis)
        val fold = executors.fold(0, { acc, executor -> acc + executor.counter() })
        executors.forEach { println("${it.key()} ${it.counter()}") }
        println("done $fold, been 20")
    }

}