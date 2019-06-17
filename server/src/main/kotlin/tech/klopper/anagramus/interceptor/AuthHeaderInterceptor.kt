package tech.klopper.anagramus.interceptor

import com.google.firebase.auth.FirebaseAuth
import org.apache.http.auth.AuthenticationException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthHeaderInterceptor : HandlerInterceptor {

    val log = LoggerFactory.getLogger(javaClass)!!
    @Throws(AuthenticationException::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (log.isDebugEnabled) {
            log.debug("Resource: ${request.requestURI}")
            log.debug("TOKEN: ${request.getHeader("ID-TOKEN")}")
        }
        try {
            FirebaseAuth.getInstance().verifyIdTokenAsync(request.getHeader("ID-TOKEN"))
        } catch (e: Exception) {
            log.error("Authentication Failed: ${e.message}")
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            throw AuthenticationException(e.message)
        }
        return super.preHandle(request, response, handler)
    }
}
