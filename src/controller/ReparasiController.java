package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ReparasiController implements Initializable {

    @FXML
    private TextField txtPemilik;
    @FXML
    private TextArea txtKeluhan;

    @FXML
    private void btnSaveRepairAction(ActionEvent actionEvent) {
    }

    @FXML
    private void btnCancelRepairAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
