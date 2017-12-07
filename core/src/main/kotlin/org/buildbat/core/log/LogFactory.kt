package org.buildbat.core.log

interface LogFactory {
    fun new(name: String): LogFile
}