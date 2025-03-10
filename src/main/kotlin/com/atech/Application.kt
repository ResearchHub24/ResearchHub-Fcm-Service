package com.atech

import com.atech.plugins.*
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureRouting()
    setUpFirebaseSDK()
}

private fun Application.setUpFirebaseSDK() {
    val serviceAccountStream = this::class.java.classLoader.getResourceAsStream("serviceAccountKey.json")
    val option = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
        .build()
    FirebaseApp.initializeApp(option)
}
