package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {
    @FXML
    private TableView historyView;
    @FXML
    private TableColumn colPlat;
    @FXML
    private TableColumn colPemilik;
    @FXML
    private TableColumn colMerk;
    @FXML
    private TableColumn colTanggal;
    @FXML
    private TableColumn colKeluhan;
    @FXML
    private TableColumn colBiaya;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
