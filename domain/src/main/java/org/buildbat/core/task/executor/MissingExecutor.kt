package org.buildbat.core.task.executor

import org.buildbat.json.EmptyJsonEntity
import org.buildbat.json.JsonEntity

class MissingExecutor(
        private val json: EmptyJsonEntity = EmptyJsonEntity()
) : Executor, JsonEntity by json {
    override fun notifyObserver() = throw IllegalStateException("There is no such executor!")
    override fun host() = throw IllegalStateException("There is no such executor!")
}