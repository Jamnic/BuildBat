package org.buildbat.core.future

class ParallelFuture(
        private val functionsToRunInFuture: MutableSet<() -> Unit> = mutableSetOf()
) {

    fun register(functionToRunInFuture: () -> Unit) {
        functionsToRunInFuture.add(functionToRunInFuture)
    }

    fun resolve(executorPool: ExecutorPool) {
        executorPool.execute(functionsToRunInFuture)
    }
}