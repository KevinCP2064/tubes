package controller;

import Entity.Kendaraan;
import dao.KendaraanDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import util.MySQLConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class KendaraanController implements Initializable {
    @FXML
    private TextField txtNoPlat;
    @FXML
    private TextField txtPemiik;
    @FXML
    private TextField txtMerek;
    @FXML
    private TextField txtWarna;
    @FXML
    private TextField txtTahun;
    @FXML
    private TextArea txtKeluhan;
    @FXML
    private TextField txtAlamat;
    @FXML
    private TableView<Kendaraan> tabKendaraan;
    @FXML
    private TableColumn<Kendaraan, String> colNoPlat;
    @FXML
    private TableColumn<Kendaraan, String> colPemilik;
    @FXML
    private TableColumn<Kendaraan, String> colKeluhan;
    private ObservableList<Kendaraan> kendaraans;
    private KendaraanDao kendaraanDao;

    @FXML
    private void btnSaveKendaraan(ActionEvent actionEvent) {

    }

    @FXML
    private void btnDeleteKendaraan(ActionEvent actionEvent) {
    }

    @FXML
    private void btnUpdateKendaraan(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kendaraans = FXCollections.observableArrayList();
        try{
            kendaraans.addAll(kendaraanDao.fetchAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        colNoPlat.setCellValueFactory(new PropertyValueFactory<>("no_plat"));
        colPemilik.setCellValueFactory(new PropertyValueFactory<>("nama_pemilik"));
        colKeluhan.setCellValueFactory(new PropertyValueFactory<>("keluhan"));
    }
}
