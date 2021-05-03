package server

import com.google.common.collect.Sets

object ChatHandler {
    private var users: MutableSet<User> = Sets.newConcurrentHashSet()

    fun addUser(user: User) {
        users.add(user)
    }
    fun removeUser(user: User) {
        user.disconnect()
        users.remove(user)
    }
    fun sendMessageByUser(message: String, user: User) {
        users
            .filter { it.hashCode() != user.hashCode() }
            .forEach { it.writer.println("(User) ${user.username}> $message") }
    }
    fun sendMessageByServer(message: String) {
        users.forEach { it.writer.println("(Server) > $message") }
    }
    fun sendMessageByServerToUser(message: String, user: User) {
        user.writer.println("(Server) > $message")
    }
}