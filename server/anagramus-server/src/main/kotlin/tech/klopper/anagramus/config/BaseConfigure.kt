package tech.klopper.anagramus.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.Parameter
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import tech.klopper.anagramus.interceptor.AuthHeaderInterceptor
import java.util.*

@Component
@EnableSwagger2
class BaseConfigure : WebMvcConfigurer {

    @Autowired
    internal var authHeaderInterceptor: AuthHeaderInterceptor? = null

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authHeaderInterceptor!!)
        super.addInterceptors(registry)
    }

    @Bean
    fun api(): Docket {
        val parameterBuilder = ParameterBuilder()
        parameterBuilder
                .name("ID-TOKEN")
                .modelRef(ModelRef("string"))
                .parameterType("header")
                .required(true).build()
        val parameters = ArrayList<Parameter>()
        parameters.add(parameterBuilder.build())
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("")
                .globalOperationParameters(parameters)
    }
}
