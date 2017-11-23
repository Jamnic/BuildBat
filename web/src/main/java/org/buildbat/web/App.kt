package org.buildbat.web

import org.buildbat.web.config.SecurityConfig
import org.buildbat.web.config.WebSocketConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Import(
        WebSocketConfig::class,
        SecurityConfig::class)
@SpringBootApplication
open class App : SpringBootServletInitializer() {

    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
        return application.sources(App::class.java)
    }

    @Bean
    open fun mappingHandlerMapping() : RequestMappingHandlerMapping {
        val requestMappingHandlerMapping = RequestMappingHandlerMapping()
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false)
        return requestMappingHandlerMapping
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(App::class.java, *args)
        }
    }
}