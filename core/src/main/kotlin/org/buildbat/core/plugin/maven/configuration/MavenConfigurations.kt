package org.buildbat.core.plugin.maven.configuration

import org.buildbat.core.plugin.maven.configuration.repository.MavenConfigurationsConfigFileRepository
import org.buildbat.core.repository.Repository

class MavenConfigurations(
        private val repository: Repository<MavenConfiguration, String> = MavenConfigurationsConfigFileRepository("classpath:maven.config.json")
) : Repository<MavenConfiguration, String> by repository