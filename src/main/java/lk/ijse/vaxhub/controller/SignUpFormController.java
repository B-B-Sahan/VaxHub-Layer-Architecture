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
import lk.ijse.vaxhub.bo.custom.PatientBO;
import lk.ijse.vaxhub.bo.custom.SignUpBO;
import lk.ijse.vaxhub.db.DbConnection;
import lk.ijse.vaxhub.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpFormController {

    @FXML
    private TextField EmailTextField;

    @FXML
    private Hyperlink logingButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private JFXButton signupButton;

    @FXML
    private TextField userNammeTextField;

    @FXML
    private TextField useridTextField;


    SignUpBO signUpBO  = (SignUpBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SignUp);


    @FXML
    void SignupButtonOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String user_id = useridTextField.getText();
        String name = userNammeTextField.getText();
        String password = passwordTextField.getText();
        String email = EmailTextField.getText();

        User user = signUpBO.checkCredential(user_id, password);
        if (user != null && user.getUser_id().equals(user_id)) {
            new Alert(Alert.AlertType.ERROR, "User ID already exists!").show();
        }else {
            boolean isSave = signUpBO.save(new User(user_id, name, password, email));
            if (isSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "Successfully Added").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Something went wrong").show();
            }
        }
    }




    @FXML
    void loginLinkOnAction(ActionEvent event) throws IOException {
        Stage stage1 = (Stage)logingButton.getScene().getWindow();
        stage1.close();
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Login Form");

        stage.show();
    }
    @FXML
    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage)this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    private void addError(TextField textField) {
        textField.setStyle("-fx-border-color: red;  -fx-background-radius: 5; -fx-background-radius: 5");
    }
    private void removeError(TextField textField) {
        textField.setStyle("-fx-border-color: green;  -fx-background-radius: 5; -fx-background-radius: 5");
    }

    @FXML
    void USEREmailOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$");
        if (!idPattern.matcher(EmailTextField.getText()).matches()) {
            addError(EmailTextField);

        }else{
            removeError(EmailTextField);
        }
    }

    @FXML
    void USERNmaeOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z ]*$");
        if (!idPattern.matcher(userNammeTextField.getText()).matches()) {
            addError(userNammeTextField);

        }else{
            removeError(userNammeTextField);
        }
    }

    @FXML
    void USERiDOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^(U)([A-z0-9.]){1,}$");
        if (!idPattern.matcher(useridTextField.getText()).matches()) {
            addError(useridTextField);

        }else{
            removeError(useridTextField);
        }
    }

    @FXML
    void USERpasswordOnKeyReleased(KeyEvent event) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z 0-9 ]*$");
        if (!idPattern.matcher(passwordTextField.getText()).matches()) {
            addError(passwordTextField);

        }else{
            removeError(passwordTextField);
        }
    }








}
