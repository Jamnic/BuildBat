package org.buildbat.core.task.executor

import org.buildbat.json.EmptyJsonEntity
import org.buildbat.json.JsonEntity

class MissingExecutor(
        private val json: JsonEntity = EmptyJsonEntity()
) : Executor, JsonEntity by json {
    override fun isFree() = throw IllegalStateException("There is no such executor!")
    override fun host() = throw IllegalStateException("There is no such executor!")
    override fun notifyObserver() {}
}