package org.buildbat.core.task

import org.apache.tomcat.util.collections.SynchronizedQueue
import org.buildbat.core.future.EmptyFutureTask
import org.buildbat.core.future.FutureTask

class TaskPool(
        private val tasks: SynchronizedQueue<FutureTask<Any>> = SynchronizedQueue(),
        private val observers: MutableSet<TaskPoolObserver> = mutableSetOf()
) {

    fun add(futureTask: FutureTask<Any>) {
        tasks.offer(futureTask)
        observers.forEach { it.notifyObserver() }
    }

    fun get(): FutureTask<Any> {
        return tasks.poll() ?: EmptyFutureTask()
    }

    fun registerObserver(observer: TaskPoolObserver) {
        this.observers.add(observer)
        observer.notifyObserver()
    }

    fun isNotEmpty(): Boolean {
        return tasks.size() > 0
    }
}