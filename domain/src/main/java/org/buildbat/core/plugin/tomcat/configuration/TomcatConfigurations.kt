package org.buildbat.core.plugin.tomcat.configuration

import org.buildbat.core.plugin.tomcat.configuration.repository.TomcatConfigurationsConfigFileRepository
import org.buildbat.core.repository.Repository

class TomcatConfigurations(
        private val repository: Repository<TomcatConfiguration, String> = TomcatConfigurationsConfigFileRepository("classpath:tomcat.config.json")
) : Repository<TomcatConfiguration, String> by repository