package com.tubes.controller;

import com.tubes.dao.KendaraanDaoImpl;
import com.tubes.dao.ServiceDaoImpl;
import com.tubes.dao.UserDaoImpl;
import com.tubes.entity.Kendaraan;
import com.tubes.entity.Service;
import com.tubes.entity.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class ServiceController implements Initializable {
    @FXML
    private TableView<Service> serviceView;
    @FXML
    private TableColumn<Kendaraan,String> colPlat;
    @FXML
    private TableColumn<User,String> colPemilik;
    @FXML
    private TableColumn<Service, Date> colTanggal;
    @FXML
    private TableColumn<Service,String> colKeluhan;
    @FXML
    private TableColumn<Service,Double> colBiaya;

    private MainController mainController;
    private ObservableList<Service> services;
    private ServiceDaoImpl serviceDao;

    private ObservableList<Kendaraan> kendaraans;
    private KendaraanDaoImpl kendaraanDao;

    private ObservableList<User> users;
    private UserDaoImpl userDao;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.serviceDao=mainController.getServiceDao();
        this.kendaraanDao=mainController.getKendaraanDao();
        this.userDao=mainController.getUserDao();
        this.services=mainController.getServices();
        this.kendaraans=mainController.getKendaraans();
        this.users=mainController.getUsers();
        try {
            services.addAll(serviceDao.fetchAll());
            kendaraans.addAll(kendaraanDao.fetchAll());
            users.addAll(userDao.fetchAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        serviceView.setItems(services);

        colPlat.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getNo_plat()));
        colPemilik.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getNama()));
        colTanggal.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getTanggal()));
        colKeluhan.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getKeterangan()));
        colBiaya.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getHarga()));
    }
}
