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
        val token = request.getHeader("ID-TOKEN")
        if (log.isDebugEnabled) {
            log.debug("Resource: ${request.requestURI}")
            log.debug("TOKEN: $token")
        }
        return when {
            canRequestSkipInitialTokenHeader(request) || token == "swagger" ->
                super.preHandle(request, response, handler)
            token == null ->
                throw AuthenticationException("Token is Empty")
            else -> {
                try {
                    FirebaseAuth.getInstance().verifyIdTokenAsync(token)
                } catch (e: Exception) {
                    log.error("Authentication Failed: ${e.message}")
                    response.status = HttpServletResponse.SC_UNAUTHORIZED
                    throw AuthenticationException(e.message)
                }
                super.preHandle(request, response, handler)
            }
        }
    }

    fun canRequestSkipInitialTokenHeader(request: HttpServletRequest): Boolean {
        return request.requestURI.contains("swagger") ||
                request.requestURI.contains("error")
    }
}
