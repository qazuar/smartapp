package com.smartai.main

import java.io.BufferedReader
import java.io.DataInputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Connector {

    private var username: String? = null
    private var password: String? = null

    constructor() { }

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun get(request: Request) {
        try {
            val response = mutableMapOf<String, String>()
            val start = System.currentTimeMillis()

            val connection = createConnection(request.server, GET)
            connection.doOutput = false

            // write
            // No write in GET

            val input = readInput(connection)

            request.response = input
            request.responseCode = connection.responseCode
            request.responseMessage = connection.responseMessage
            request.responseTime = System.currentTimeMillis() - start
        } catch (exception: Exception) {
            request.exception = exception
        }
    }

    private fun createConnection(address: String, method: String): HttpURLConnection {
        val url = URL(address)
        val connection = url.openConnection() as HttpURLConnection

        if (username != null && password != null) {
            val login = "$username:$password"
            val auth = "Basic " + String(Base64.getEncoder().encode(login.toByteArray()))
            connection.setRequestProperty("Authorization", auth)
        }

        connection.requestMethod = method
        connection.connectTimeout = CONNECT_TIMEOUT
        connection.readTimeout = READ_TIMEOUT

        return connection
    }

    private fun readInput(connection: HttpURLConnection): String {
        val inputStream: DataInputStream =
            DataInputStream(connection.errorStream ?: connection.inputStream)
        val reader: BufferedReader = BufferedReader(InputStreamReader(inputStream, Charsets.UTF_8))

        val stringBuilder = StringBuilder()
        var line = reader.readLine()

        while (line != null) {
            stringBuilder.append(line + System.lineSeparator())
            line = reader.readLine()
        }

        reader.close()

        return stringBuilder.toString()
    }

    companion object {
        private const val CONNECT_TIMEOUT = 60000
        private const val READ_TIMEOUT = 60000

        private const val GET = "GET"
        private const val POST = "POST"
        private const val PUT = "PUT"
        private const val DELETE = "DELETE"
    }

}