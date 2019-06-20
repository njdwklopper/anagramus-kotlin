package tech.klopper.anagramus.controller

import com.google.firebase.auth.FirebaseToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import tech.klopper.anagramus.helper.FirebaseAuthLoader

@RestController
class MeController(@Autowired private val loader: FirebaseAuthLoader) {
    @GetMapping("/me")
    fun me(@RequestHeader(value = "ID-TOKEN") idToken: String): ResponseEntity<FirebaseToken> {
        val fbt = loader.verifyIdToken(idToken)
        return ResponseEntity.ok(fbt)
    }
}
