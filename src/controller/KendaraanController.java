package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class KendaraanController implements Initializable {
    @FXML
    private TextField txtNoPlat;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TextField txtMerek;
    @FXML
    private TextField txtWarna;
    @FXML
    private TextField txtTahunBuat;
    @FXML
    private TextArea txtKeluhan;
    @FXML
    private ComboBox comboUser;
    @FXML
    private TableView KendaraanView;
    @FXML
    private TableColumn colPlat;
    @FXML
    private TableColumn colPemilik;
    @FXML
    private TableColumn colKeluhan;

    @FXML
    private void btnSaveMobilAction(ActionEvent actionEvent) {
    }

    @FXML
    private void btnDeleteMobilAction(ActionEvent actionEvent) {
    }

    @FXML
    private void btnUpdateMobilAction(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
