package com.atech.model


enum class Type {
    RESEARCH, FACULTY, SELECTION, ADVERTISEMENT
}

data class NotificationModel(
    val message: Message
)

data class Message(
    val topic: String,
    val notification: Notification,
    val data: Data? = null
)

data class Notification(
    val title: String, val body: String
)

data class Data(
    val key: String,
    val created: String,
    val type: Type = Type.RESEARCH,
    val image: String? = null
)