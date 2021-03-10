package com.test.app.view_model

import com.test.app.model.ExceptionHandler
import javafx.scene.control.Alert

object AlertPopUP {
    fun noRecordFound(errorHandler: ExceptionHandler) {
        val msg = Alert(Alert.AlertType.ERROR)
        msg.title = "No Records Found"
        msg.headerText = "Error Code " + errorHandler.getErrorCode() + " Occured"
        msg.contentText = "No records found for Your Request " + errorHandler.getErrorMessage()
        msg.showAndWait()
    }

    fun generalError(ex: Exception) {
        val classMethod = object : Any() {}.javaClass.enclosingMethod.name
        val msg = Alert(Alert.AlertType.ERROR)
        msg.title = "Error Occured!.."
        msg.headerText = null
        msg.contentText = "Error Occured, Try Again!..Check method " + classMethod + "Exception found with Mouse click :" + ex
        msg.showAndWait()
    }
    @JvmStatic
    fun specificGeneralError(ex: Exception, className: String) {
        val classMethod = object : Any() {}.javaClass.enclosingMethod.name
        val msg = Alert(Alert.AlertType.ERROR)
        msg.title = "Error Occured! from Class :$className"
        msg.headerText = null
        msg.contentText = "Error Occured, Try Again!..Check method " + classMethod + "Exception found with Mouse click :" + ex
        msg.showAndWait()
    }
}
