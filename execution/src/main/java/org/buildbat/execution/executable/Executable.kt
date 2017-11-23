package org.buildbat.execution.executable

import org.buildbat.execution.process.Process

// TODO refactor this senseless name
interface Executable {
    fun execute(): Process
}