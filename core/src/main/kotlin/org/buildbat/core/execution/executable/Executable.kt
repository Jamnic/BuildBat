package org.buildbat.core.execution.executable

import org.buildbat.core.execution.process.Process

// TODO refactor this senseless name
interface Executable {
    fun execute(): Process
}