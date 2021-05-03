package server

import java.lang.Exception
import java.net.Socket

class ConnectionHandler(private val connection: Socket) : Runnable {
    override fun run() {
        val user = User(null, connection)
        user.username = user.reader.readLine()
        if (user.username != null) {
            ChatHandler.addUser(user)
            while (true) {
                var message: String? = user.reader.readLine()
                if (message == null) {
                    ChatLogger.log("dropped : ${user.socket.inetAddress} ${user.socket.port}")
                    break
                }
                if (message.startsWith(':')) {
                    if (message == ":quit") {
                        user.writer.println(":quit")
                        ChatLogger.log("disconnected : ${user.socket.inetAddress} ${user.socket.port}")
                        break
                    }
                    try {
                        val command = message.split(' ')[0]
                        if (command == ":update") {
                            val oldUsername = user.username
                            user.username = message.replaceFirst("$command ", "")
                            ChatHandler.sendMessageByServer("$oldUsername is now ${user.username}")
                        } else {
                            ChatHandler.sendMessageByServerToUser("Invalid command", user)
                        }
                    } catch (e: Exception) {
                        ChatHandler.sendMessageByServerToUser("Invalid command", user)
                        println(e.message)
                    }
                    continue
                }
                ChatHandler.sendMessageByUser(message, user)
            }
        }
        ChatHandler.removeUser(user)
    }
}