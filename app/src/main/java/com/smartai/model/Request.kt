package com.smartai.model

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class Request {

    var documentBuilder: DocumentBuilder? = null

    lateinit var server: String
    var payload: String? = null

    var response: String? = null
    var responseCode: Int? = null
    var responseMessage: String? = null
    var responseTime: Long? = null

    var exception: Exception? = null

    val strings = mutableListOf<String>()

    companion object {
        @Synchronized
        fun newInstance(): Request {
            val request = Request()

            val factory = DocumentBuilderFactory.newInstance()
            request.documentBuilder = factory.newDocumentBuilder()

            return request

        }
    }

}