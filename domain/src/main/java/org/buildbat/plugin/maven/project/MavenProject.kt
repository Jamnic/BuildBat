package org.buildbat.plugin.maven.project

import org.buildbat.plugin.maven.configuration.MavenConfiguration
import org.buildbat.project.Project

interface MavenProject : Project {
    fun mavenConfig(): MavenConfiguration
}