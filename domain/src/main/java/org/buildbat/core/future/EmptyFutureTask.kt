package org.buildbat.core.future

class EmptyFutureTask<out T> : FutureTask<T> {
    override fun thread() = Thread()
    override fun <MAPPED_RESULT> then(mapFunction: (T) -> MAPPED_RESULT): FutureTask<MAPPED_RESULT> = EmptyFutureTask()
}