package org.buildbat.core.task

// TODO Remove singleton anti pattern
enum class TaskPoolProvider {
    INSTANCE;

    val taskPool: TaskPool = TaskPool()
}