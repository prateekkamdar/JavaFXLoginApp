package com.test.app.model

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class APIControlling {
    private val USER_AGENT = "Mozilla/5.0"
    @Throws(Exception::class)
    fun sendingPostRequest(user: User): ExceptionHandler {
        val url = "http://private-222d3-homework5.apiary-mock.com/api/login"
        val urlObject = URL(url)
        val connection = urlObject.openConnection() as HttpURLConnection

        // Setting basic post request
        connection.requestMethod = "POST"
        connection.setRequestProperty("User-Agent", USER_AGENT)
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("IMSI", "357175048449937")
        connection.setRequestProperty("IMEI", "510110406068589")
        val postJsonData = """
            {
            "username": ${user.getuName()}, "password": ${user.getuPassword()}
            }
            
            """.trimIndent()
        // Send post request
        connection.doOutput = true
        val dataOutputStream = DataOutputStream(connection.outputStream)
        dataOutputStream.writeBytes(postJsonData)
        dataOutputStream.flush()
        dataOutputStream.close()
        val `in` = BufferedReader(InputStreamReader(connection.inputStream))
        var output: String?
        val response = StringBuffer()
        while (`in`.readLine().also { output = it } != null) {
            response.append(output)
        }
        `in`.close()
        val jsonParser = JSONParser()
        val responseObj = jsonParser.parse(response.toString()) as JSONObject
        val errorCode = responseObj["errorCode"].toString()
        val errorMessage = responseObj["errorMessage"].toString()
        val errorHandler = ExceptionHandler()
        errorHandler.setErrorCode(errorCode)
        errorHandler.setErrorMessage(errorMessage)

        return errorHandler
    }
}
