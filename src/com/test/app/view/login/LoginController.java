package com.test.app.view.login;

import com.test.app.model.ExceptionHandler;
import com.test.app.model.FileHandler;
import com.test.app.model.User;
import com.test.app.view_model.DataValidation;
import com.test.app.view_model.UserViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label userval;

    @FXML
    private Label passwordval;

    public static ExceptionHandler errHandler;
    public static User user;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        rootPane.setStyle("-fx-background-color:"+UserViewModel.setGUI());
        setImageProperties();
    }
    @FXML
    protected void switchToDark(){
        rootPane.setStyle("-fx-background-color: #2b384b");
        FileHandler.setGUIState("dark");

    }
    @FXML
    protected void switchToLight(){
        rootPane.setStyle("-fx-background-color: #FFFFFF");
        FileHandler.setGUIState("light");
    }

    @FXML
    public void clearUserLabel(){
        userval.setText("");
    }

    @FXML
    public void clearPasswordLabel(){
        passwordval.setText("");
    }

    private void clearFields(){
        username.setText("");
        password.setText("");
        userval.setText("");
        passwordval.setText("");
    }

    private boolean dataValidate(){

        boolean returnVal = false;

        if(DataValidation.TextFieldNotEmpty(username.getText())
                && DataValidation.TextFieldNotEmpty(password.getText())
                //Checking for maximum Characters
                && DataValidation.isValidMaximumLength(username.getText(),30)
                && DataValidation.isValidMaximumLength(password.getText(),16)){
            returnVal = true;
        }
        return returnVal;
    }
    private void dataValidateMessage(){

        //checking for being empty
        if(!(DataValidation.TextFieldNotEmpty(username.getText())
                && DataValidation.TextFieldNotEmpty(password.getText()))){


            DataValidation.TextFieldNotEmpty(username.getText(),userval ,"User Name cannot be empty!");
            DataValidation.TextFieldNotEmpty(password.getText(), passwordval,"Password cannot be empty!");
            //checking for exceeding limit

        }
        if(!(DataValidation.isValidMaximumLength(username.getText(),30)
                && DataValidation.isValidMaximumLength(password.getText(),16))){


            DataValidation.isValidMaximumLength(username.getText(),30, userval,"User Name too Long!..(Limit 30)");
            DataValidation.isValidMaximumLength(password.getText(),16, passwordval,"Password too Long!..(Limit 16)");

        }
    }
    @FXML
    protected void login(ActionEvent actionEvent){
        User user= new User();
        UserViewModel userViewModel = new UserViewModel();
        if(dataValidate()){

            user.setuName(username.getText());
            user.setuPassword(password.getText());
            userViewModel.userValidate(user);
            if(user == null){
                clearFields();
            }else{
                userViewModel.loadHome(rootPane);
            }
        }else{
            dataValidateMessage();
        }
    }
    private void setImageProperties(){
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
    }
}
