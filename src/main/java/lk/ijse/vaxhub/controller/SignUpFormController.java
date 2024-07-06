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
import lk.ijse.vaxhub.db.DbConnection;

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





    @FXML
    void SignupButtonOnAction(ActionEvent event) {
        String user_id = useridTextField.getText();
        String name = userNammeTextField.getText();
        String password = passwordTextField.getText();
        String email = EmailTextField.getText();

        saveUser(user_id, name, password,email);
    }

    private void saveUser(String user_id, String name, String password, String email) {
        try {
            String sql = "INSERT INTO user VALUES(?, ?, ?,?)";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, user_id);
            pstm.setObject(2, name);
            pstm.setObject(3, password);
            pstm.setObject(4, email);
            if(pstm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();
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
