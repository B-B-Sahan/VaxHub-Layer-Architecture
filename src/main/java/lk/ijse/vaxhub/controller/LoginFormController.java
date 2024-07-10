package lk.ijse.vaxhub.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.vaxhub.bo.BOFactory;
import lk.ijse.vaxhub.bo.custom.EmployeeBO;
import lk.ijse.vaxhub.bo.custom.LoginBO;
import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {

    @FXML
    private Hyperlink SignUpHyperLink;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton loginButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField useridTextField;

    LoginBO loginBO  = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Login);

    @FXML
    void loginButtonOnAction(ActionEvent event) {
        String userId = useridTextField.getText();
        String pw = passwordTextField.getText();
        try {
            User user = loginBO.checkCredential(userId, pw);
            if (user != null) {
                String dbpw = user.getPassword();
                if (dbpw.equals(pw)) {
                    navigateToTheDashboard();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Password is incorrect!").show();
                }
            } else {
                new Alert(Alert.AlertType.INFORMATION, "user id not found!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Database error: " + e.getMessage()).show();
            e.printStackTrace();
        }catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
            e.printStackTrace();
        }


    }




    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboardmain_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");

    }
    @FXML
    void SignupLinkOnAction(ActionEvent event) throws IOException {
        Stage stage1 = (Stage)SignUpHyperLink.getScene().getWindow();
        stage1.close();
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/signup_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("SignUp Form");

        stage.show();
    }

    public void cancelButtonOnAction(ActionEvent event) {
    }


    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    @FXML
    void LOGINuIdOnKeyReleased(KeyEvent event) {


        Pattern idPattern = Pattern.compile("^(U)([A-z0-9.]){1,}$");
        if (!idPattern.matcher(useridTextField.getText()).matches()) {
            addError(useridTextField);
            loginButton.setDisable(true);
        }else{
            removeError(useridTextField);
            loginButton.setDisable(false);
        }
    }

    @FXML
    void LOGpwuIdOnKeyReleased(KeyEvent event) {


        Pattern idPattern = Pattern.compile("^[a-zA-Z 0-9 ]*$");


        if (!idPattern.matcher(passwordTextField.getText()).matches()) {
            addError(passwordTextField);
            loginButton.setDisable(true);
        }else{
            removeError(passwordTextField);
            loginButton.setDisable(false);
        }
    }






}
