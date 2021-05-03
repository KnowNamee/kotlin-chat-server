package client

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.lang.Exception
import java.net.Socket

import kotlin.concurrent.thread
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size !in 2..3) {
        System.err.println("Invalid arguments!")
        exitProcess(1)
    }
    var ip = "127.0.0.1"
    var port = 8081
    var username  = "def"
    try {
        for (arg in args) {
            val (argname, argval) = CommandLineArgsParser.parse(arg)
            when (argname) {
                "ip" -> {
                    ip = argval
                }
                "port" -> {
                    if (argval.toIntOrNull() == null)
                        throw Exception()
                    port = argval.toInt()
                }
                "username" -> {
                    username = argval
                }
                else -> {
                    throw Exception()
                }
            }
        }
    } catch (e: Exception) {
        System.err.println("Invalid arguments!")
        exitProcess(1)
    }
    val socket = Socket(ip, port)
    val stdinReader = BufferedReader(InputStreamReader(socket.getInputStream()))
    val stdoutWriter = PrintWriter(socket.getOutputStream(), true)
    stdoutWriter.println(username)
    thread {
        while (socket.isConnected) {
            val message: String? = stdinReader.readLine()
            if (message !is String) {
                exitProcess(1)
            }
            if (message == ":quit") {
                exitProcess(0)
            }
            println(message)
        }
    }
    while (socket.isConnected) {
        val message: String = readLine().toString()
        stdoutWriter.println(message)
        if (message == ":quit") {
            break
        }
    }
    stdinReader.close()
    stdoutWriter.close()
    socket.close()
}