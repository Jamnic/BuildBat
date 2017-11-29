package org.buildbat.core.task

class TaskPoolProvider {

    // TODO  bad design, Singleton as antipattern
    companion object {
        val taskPool: TaskPool = TaskPool()
    }
}