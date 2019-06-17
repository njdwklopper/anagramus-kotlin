package tech.klopper.anagramus.core.service.home

import com.google.gson.JsonObject
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import tech.klopper.anagramus.core._base.logger.BaseLogger
import tech.klopper.anagramus.core._base.session.FirebaseHandler

class HomeHandler(
    val view: HomeViewHandler,
    val firebase: FirebaseHandler,
    val log: BaseLogger
) : HomeHandle {

    override fun pressButton() {
        log.d("Pressed Button")
        firebase.getUser().getIdToken(true).addOnCompleteListener {
            if (it.isSuccessful) {
                // Retrofit builder
                val retrofitBuilder = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                log.e("ERROR : ${it.result!!.token}")
                // Create service
                val gcpService = retrofitBuilder.create(MEService::class.java)

                doAsync {
                    val apiCall = gcpService.getMe("${it.result!!.token}").execute()
                    log.d("API Call ${apiCall.body().toString()}")

                    uiThread {
                        view.handleResponse("Button Pressed")
                    }
                }
            } else {
                throw Exception("Token error")
            }
        }
    }
}

interface HomeHandle {
    fun pressButton()
}

interface HomeViewHandler {
    fun handleResponse(message: String)
}

interface MEService {
    @GET("/me")
    fun getMe(@Header("ID-TOKEN") token: String): Call<JsonObject>
}
