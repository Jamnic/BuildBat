package org.buildbat.core.plugin.maven.project

import org.buildbat.core.plugin.maven.configuration.MavenConfiguration
import org.buildbat.core.plugin.project.Project

interface MavenProject : Project {
    fun mavenConfiguration(): MavenConfiguration
}