package tech.klopper.anagramus.controller

import com.google.firebase.auth.FirebaseAuth
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import tech.klopper.anagramus.model.Me

@RestController
class Me {


    @GetMapping("/me")
    fun me(@RequestHeader(value = "ID-TOKEN") idToken: String): ResponseEntity<Me> {
        val fbt = Me(FirebaseAuth.getInstance().verifyIdToken(idToken))
        return ResponseEntity.ok(fbt)
    }
}
