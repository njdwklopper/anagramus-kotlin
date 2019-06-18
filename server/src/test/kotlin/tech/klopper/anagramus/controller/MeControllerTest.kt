package tech.klopper.anagramus.controller

import com.google.firebase.auth.FirebaseToken
import com.google.firebase.auth.FirebaseTokenTestFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import tech.klopper.anagramus.helper.FirebaseAuthLoader
import tech.klopper.anagramus.model.MeModel

class MeControllerTest {

    private lateinit var me: MeController
    lateinit var fbh: FirebaseAuthLoader
    private val claims: MutableMap<String, Any> = mutableMapOf()
    lateinit var token: FirebaseToken

    @Before
    fun setUp() {
        claims["sub"] = "sub"
        fbh = FirebaseTokenTestFactory(claims)
        token = fbh.verifyIdToken("1234")
        me = MeController(fbh)
    }

    @Test
    fun me() {
        Assert.assertEquals(
                MeModel(fbh.verifyIdToken("1234")).token.claims,
                me.me("1234").body!!.token.claims
        )
    }
}
