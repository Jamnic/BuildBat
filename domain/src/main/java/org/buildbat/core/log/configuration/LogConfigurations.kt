package org.buildbat.core.log.configuration

import org.buildbat.core.log.configuration.repository.LogConfigurationsConfigFileRepository
import org.buildbat.core.repository.Repository

class LogConfigurations(
        private val repository: Repository<LogConfiguration, String> = LogConfigurationsConfigFileRepository("classpath:log.config.json")
) : Repository<LogConfiguration, String> by repository