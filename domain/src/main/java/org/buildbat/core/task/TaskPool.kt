package org.buildbat.core.task

import org.apache.tomcat.util.collections.SynchronizedQueue
import org.buildbat.core.futuretask.EmptyFutureTask
import org.buildbat.core.futuretask.FutureTask

class TaskPool(
        private val tasks: SynchronizedQueue<FutureTask<Any>> = SynchronizedQueue(),
        private val observers: MutableList<TaskPoolObserver> = mutableListOf()
) {

    fun isNotEmpty(): Boolean {
        return tasks.size() > 0
    }

    fun get(): FutureTask<Any> {
        return tasks.poll() ?: EmptyFutureTask()
    }

    fun registerObserver(observer: TaskPoolObserver) {
        observers.add(observer)
        observer.notifyObserver()
    }

    fun add(futureTask: FutureTask<Any>) {
        tasks.offer(futureTask)
        observers.forEach { it.notifyObserver() }
    }
}