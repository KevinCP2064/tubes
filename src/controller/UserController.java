package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelp;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtRePass;
    @FXML
    private TableView UserView;
    @FXML
    private TableColumn colNama;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colTelp;
    @FXML
    private TableColumn colUsername;

    @FXML
    private void btnSaveUserAction(ActionEvent actionEvent) {
    }

    @FXML
    private void btnDeleteUserAction(ActionEvent actionEvent) {
    }

    @FXML
    private void btnUpdateUserAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
