package org.buildbat.core.future

import java.util.concurrent.ConcurrentLinkedQueue

class ExecutorPool(
        private val executors: MutableList<Executor> = mutableListOf()
) {

    fun execute(functions: Set<() -> Unit>) {
        val queue = ConcurrentLinkedQueue(functions)

        val thread = Thread(Runnable {
            while (queue.isNotEmpty() || executors.any { !it.isFree() }) {
                executors.forEach { executor ->
                    if (executor.isFree() && queue.isNotEmpty())
                        executor.execute(queue.poll())
                }
            }
        })
        thread.start()
        thread.join()
    }
}