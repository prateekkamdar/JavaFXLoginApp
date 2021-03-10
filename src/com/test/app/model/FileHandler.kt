package com.test.app.model

import com.test.app.view_model.AlertPopUP.specificGeneralError
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

object FileHandler {
    private val jsonFilePath = File("../TestApp/data.json")
    private const val filePath = "../TestApp/data.json"

    @JvmStatic
    var gUIState: String?
        get() {
            val parserReader = JSONParser()
            var newState: String? = null

            //Get State
            val jsonObject = JSONObject()
            if (!jsonFilePath.exists() && !jsonFilePath.isDirectory) {
                createJsonObject()
            } else {
                try {
                    val obj = parserReader.parse(FileReader(filePath))
                    val jsonReadObject = obj as JSONObject
                    newState = jsonReadObject["GUI State"] as String?
                } catch (exception: Exception) {
                    specificGeneralError(exception, "FileHandler.getGUIState()")
                }
            }
            return newState
        }
        set(state) {
            //Set State
            val jsonObject = JSONObject()
            if (!jsonFilePath.exists() && !jsonFilePath.isDirectory) {
                createJsonObject()
            } else {
                try {
                    //Udating Existing key-value pairs into the json object
                    jsonObject["GUI State"] = state
                    val fileWritter = FileWriter(filePath)
                    fileWritter.write(jsonObject.toJSONString())
                    fileWritter.close()
                } catch (exception: IOException) {
                    specificGeneralError(exception, "FileHandler.setGUIState()")
                }
            }
        }

    private fun createJsonObject() {
        //Creating a JSON Object object
        val jsonObject = JSONObject()
        try {
            //Inserting Default key-value pairs into the json object
            jsonObject["GUI State"] = "Light"
            val fileWritter = FileWriter(filePath)
            fileWritter.write(jsonObject.toJSONString())
            fileWritter.close()
        } catch (exception: IOException) {
            specificGeneralError(exception, "FileHandler.setGUIState()")
        }
    }
}