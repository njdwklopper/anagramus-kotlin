package tech.klopper.anagramus.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream
import javax.annotation.PostConstruct

@Configuration
class FirebaseConfig {

    @Value("\${tech.klopper.anagramus.firebase.service.cred.path}")
    private val configPath: String? = null

    @Value("\${tech.klopper.anagramus.firebase.database.url}")
    private val databaseUrl: String? = null

    @Bean
    fun firebaseDatabase(): DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }

    @PostConstruct
    fun init() {
        try {
            println("Firebase configPath: $configPath")
            val serviceAccount = FileInputStream(configPath)
            val options = FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(databaseUrl)
                    .build()

            FirebaseApp.initializeApp(options)
            FirebaseDatabase.getInstance(FirebaseApp.getInstance()).setPersistenceEnabled(true)
            println("Firebase Initialised")
            println("Firebase DB: " + options.databaseUrl)
        } catch (e: Exception) {
            println("Trying to login to firebase failed. Reason: " + e.message)
        }
    }
}
