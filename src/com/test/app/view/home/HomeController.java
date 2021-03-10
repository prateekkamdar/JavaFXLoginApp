package com.test.app.view.home;


import com.test.app.model.FileHandler;
import com.test.app.view.login.LoginController;
import com.test.app.view_model.UserViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private AnchorPane rootArPane;

    @FXML
    private Label welcomeLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootArPane.setStyle("-fx-background-color:"+UserViewModel.setGUI());
        setUser();
    }
    @FXML
    protected void switchToDark(){
        rootArPane.setStyle("-fx-background-color: #2b384b");
        FileHandler.setGUIState("dark");

    }
    @FXML
    protected void switchToLight(){
        rootArPane.setStyle("-fx-background-color: #FFFFFF");
        FileHandler.setGUIState("light");
    }
    //setting mode and user
    private void setUser(){
        welcomeLabel.setText("Hello Welcome "+LoginController.user.getuName() + " !!");
    }
}
