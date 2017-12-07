package org.buildbat.core.plugin.git.project

import org.buildbat.core.plugin.project.Project

interface GitProject : Project {
    fun repository(): String
}