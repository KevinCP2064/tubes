package com.kevin.controller;

import com.kevin.entity.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ClubController {
    @FXML
    private TextField txtClubName;
    private MainController mainController;

    @FXML
    private void saveClubActionBtn(ActionEvent actionEvent) {
        if(txtClubName.getText().trim().isEmpty()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tolong isi nama club");
            alert.showAndWait();
        }else{
            Club club=new Club();
            club.setName(txtClubName.getText().trim());
            try {
                mainController.getClubDao().addData(club);
                mainController.getClubs().clear();
                mainController.getClubs().addAll(mainController.getClubDao().showAllData());
                txtClubName.clear();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @FXML
    private void cancelSaveClubActionBtn(ActionEvent actionEvent) {
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
