package org.buildbat.core.future

class EmptyFutureTask<out T> : FutureTask<T> {
    override fun execute(): () -> T = throw IllegalStateException("There is no such task!")
    override fun thread() = Thread()
    override fun <MAPPED_RESULT> then(mapFunction: (T) -> MAPPED_RESULT): FutureTask<MAPPED_RESULT> = EmptyFutureTask()
}