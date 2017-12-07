package org.buildbat.poc

import org.buildbat.core.futuretask.BaseFutureTask
import org.buildbat.core.task.TaskPoolProvider
import org.buildbat.core.task.executor.LocalExecutor
import org.junit.Test
import java.lang.Thread.sleep
import java.util.concurrent.atomic.AtomicInteger

class TaskExecutingTest {

    @Test
    fun shouldWorkOnAllTasks() {

        val taskPool = TaskPoolProvider.taskPool

        val list = listOf(
                LocalExecutor("Ann", "localhost")
//                LocalExecutor("Bob", "localhost"),
//                LocalExecutor("Cynthia", "localhost"),
//                LocalExecutor("Dean", "localhost")
        )

        val counter = AtomicInteger(0)

        list.forEach { taskPool.registerObserver(it) }

        val start = System.currentTimeMillis()
        for (i in 0..99) {
            taskPool.add(BaseFutureTask<Any> {
                sleep(100)
                counter.addAndGet(1)
                print(i)
            })
        }

        while (taskPool.isNotEmpty() || list.any { !it.isFree() }) {
        }

        println()
        println("Work time: ${System.currentTimeMillis() - start}")
        println("Performed tasks $counter")
        // 2 threads 5074, 5 threads 6203
        // 2 coroutines 5103, 5 coroutines 3172
    }

}