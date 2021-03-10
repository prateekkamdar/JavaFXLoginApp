package com.test.app.view_model

import com.test.app.model.APIControlling
import com.test.app.model.ExceptionHandler
import com.test.app.model.FileHandler
import com.test.app.model.User
import com.test.app.view.login.LoginController
import javafx.fxml.FXMLLoader
import javafx.scene.layout.AnchorPane
import java.io.IOException

class UserViewModel {
    fun userValidate(user: User?) {
        val apiHandler = APIControlling()
        val errorHandler: ExceptionHandler
        try {
            errorHandler = apiHandler.sendingPostRequest(user!!)
            if (errorHandler.validateErrorCode()) {
                LoginController.staticErrorHandler = errorHandler
                LoginController.staticUser = user
            } else {
                AlertPopUP.noRecordFound(errorHandler)
            }
        } catch (ex: Exception) {
            AlertPopUP.generalError(ex)
        }
    }

    fun loadHome(rootpane: AnchorPane) {
        try {
            val pane = FXMLLoader.load<AnchorPane>(javaClass.getResource("/com/test/app/view/home/home.fxml"))
            rootpane.children.setAll(pane)
        } catch (ex: IOException) {
            AlertPopUP.generalError(ex)
        }
    }
    companion object {
        @JvmStatic
        fun setGUI(): String {
            return if (FileHandler.gUIState == "dark") "#2b384b" else "#FFFFFF"
        }
    }
}
