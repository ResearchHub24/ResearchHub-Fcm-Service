package com.atech.plugins

import com.atech.routes.sendFacultyNotification
import com.atech.routes.sendPushNotificationToUser
import com.atech.routes.sendResearchNotification
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        sendFacultyNotification()
        sendResearchNotification()
        sendPushNotificationToUser()
    }
}
