package com.atech.routes

import com.atech.model.NotificationModel
import com.atech.model.Type
import com.atech.model.toMessage
import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.sendFacultyNotification() {
    route("/send/topics/FacultiesNotifications") {
        post {
            val body = this.call.receiveNullable<NotificationModel>() ?: kotlin.run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Invalid body")
                return@post
            }
            if (body.message.topic.isNullOrBlank()) {
                call.respond(status = HttpStatusCode.BadRequest, message = "Topic must be provided")
                return@post
            }
            FirebaseMessaging.getInstance()
                .send(body.toMessage(Type.FACULTY))
            call.respond(HttpStatusCode.OK)
        }
    }
}

fun Route.sendPushNotificationToUser() {
    route("/send/user") {
        post {
            val body = call.receiveNullable<NotificationModel>() ?: kotlin.run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Invalid body")
                return@post
            }
            if (body.message.to.isNullOrBlank()) {
                call.respond(status = HttpStatusCode.BadRequest, message = "Token must be provided")
                return@post
            }
            FirebaseMessaging.getInstance()
                .send(body.toMessage(Type.USER_STUDENT))
            call.respond(HttpStatusCode.OK)
        }
    }
}

fun Route.sendResearchNotification() {
    route("/send/topics/ResearchPublish") {
        post {
            val body = call.receiveNullable<NotificationModel>() ?: kotlin.run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Invalid body")
                return@post
            }
            if (body.message.topic.isNullOrBlank()) {
                call.respond(status = HttpStatusCode.BadRequest, message = "Topic must be provided")
                return@post
            }
            FirebaseMessaging.getInstance()
                .send(body.toMessage(Type.RESEARCH))
            call.respond(HttpStatusCode.OK)
        }
    }
}