package com.smartai.main

import com.smartai.model.Connector
import com.smartai.model.Request

class Sandbox {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            test()
        }

        fun test() {
            val connector = Connector("kundecenter", "kundecenter")
            val request = Request.newInstance()

            request.server = "http://salgskerne-tst1.dsb.dk/sales/version"

            connector.get(request)

            println(request.response)
        }

    }

}