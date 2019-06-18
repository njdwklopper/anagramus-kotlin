package tech.klopper.anagramus.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import tech.klopper.anagramus.helper.FirebaseAuthLoader
import tech.klopper.anagramus.model.MeModel

@RestController
class MeController(@Autowired private val loader: FirebaseAuthLoader) {
    @GetMapping("/me")
    fun me(@RequestHeader(value = "ID-TOKEN") idToken: String): ResponseEntity<MeModel> {
        val fbt = MeModel(loader.verifyIdToken(idToken))
        return ResponseEntity.ok(fbt)
    }
}
