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
    private TextField txtPemilik;
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
    public Kendaraan selectedItem;
    private KendaraanDao kendaraanDao;
    int hitung;

    public KendaraanDao getKendaraanDao(){
        if(kendaraanDao == null){
            kendaraanDao = new KendaraanDao();
        }
        return kendaraanDao;
    }
    public ObservableList<Kendaraan> getkendaraans() throws SQLException, ClassNotFoundException {
        if (kendaraans == null){
            kendaraans = FXCollections.observableArrayList();
            kendaraans.addAll(getKendaraanDao().fetchAll());
        }
        return kendaraans;
    }
    public void refresh() throws SQLException, ClassNotFoundException {
        getkendaraans().clear();
        getkendaraans().addAll(getKendaraanDao().fetchAll());
    }

    public void clearForm(){
        txtNoPlat.clear();
        txtPemilik.clear();
        txtMerek.clear();
        txtTahun.clear();
        txtWarna.clear();
        txtAlamat.clear();
        txtKeluhan.clear();
        tabKendaraan.getSelectionModel().clearSelection();
        selectedItem = null;

    }


    @FXML
    private void btnSaveKendaraan(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (txtNoPlat.getText().isEmpty() || txtPemilik.getText().isEmpty() || txtPemilik.getText().isEmpty() ||
                txtMerek.getText().isEmpty() || txtWarna.getText().isEmpty() || txtTahun.getText().isEmpty() ||
                txtKeluhan.getText().isEmpty()) {
            alert.setTitle("Error");
            alert.setContentText("Please fill category/name/id");
            alert.show();
        } else {
            Kendaraan i = new Kendaraan();
            i.setNo_plat(txtNoPlat.getText());
            hitung = (int) kendaraans.stream().filter(p -> p.getNo_plat().equalsIgnoreCase(txtNoPlat.getText())).count();
            if (hitung > 0) {
                alert.setTitle("Error");
                alert.setContentText("Duplicate item name");
                alert.show();
            } else {
                i.setNo_plat(txtNoPlat.getText().trim());
                i.setNama_pemilik(txtPemilik.getText().trim());
                i.setMerk(txtMerek.getText().trim());
                i.setWarna(txtWarna.getText());
                i.setTahun(txtTahun.getText());
                i.setKeluhan((txtKeluhan.getText()));
            }
        }
    }

    @FXML
    private void btnDeleteKendaraan(ActionEvent actionEvent) {
    }

    @FXML
    private void btnUpdateKendaraan(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!txtNoPlat.getText().trim().isEmpty()) {
            selectedItem.setNo_plat((txtNoPlat.getText().trim()));
            getKendaraanDao().updateData(selectedItem);
            refresh();
            clearForm();
        }
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
