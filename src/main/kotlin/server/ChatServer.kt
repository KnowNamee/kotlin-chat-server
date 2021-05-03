package server

import java.lang.Exception
import java.net.ServerSocket

object ChatServer {
    fun serve(port: Int) {
        try {
            ChatLogger.log("Start running chat server on port $port")
            val serverSocket = ServerSocket(port)
            while (true) {
                val connection = serverSocket.accept()
                ChatLogger.log("connected : ${connection.inetAddress} ${connection.port}")
                Thread(ConnectionHandler(connection)).start()
            }
        } catch (e: Exception) {
            System.err.println(e.message)
        }
    }
}