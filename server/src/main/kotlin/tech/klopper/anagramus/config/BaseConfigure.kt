package tech.klopper.anagramus.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import tech.klopper.anagramus.interceptor.AuthHeaderInterceptor

@Component
class BaseConfigure : WebMvcConfigurer {

    @Autowired
    internal var authHeaderInterceptor: AuthHeaderInterceptor? = null

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authHeaderInterceptor!!)
        super.addInterceptors(registry)
    }
}
