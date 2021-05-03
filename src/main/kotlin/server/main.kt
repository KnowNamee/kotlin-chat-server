package server

import java.lang.Exception
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty() || args.size > 1) {
        System.err.println("Invalid argument!")
        exitProcess(1)
    }
    try {
        val (port, value) = CommandLineArgsParser.parse(args[0])
        if (port != "port" || value.toIntOrNull() == null)
            throw Exception()
        ChatServer.serve(value.toInt())
    } catch (e: Exception) {
        System.err.println("Invalid argument!")
        exitProcess(1)
    }
}