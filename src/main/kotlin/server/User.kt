package server

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

data class User(var username: String?, var socket: Socket) {
    val writer = PrintWriter(socket.getOutputStream(), true)
    val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

    fun disconnect() {
        writer.close()
        reader.close()
        socket.close()
    }
}
