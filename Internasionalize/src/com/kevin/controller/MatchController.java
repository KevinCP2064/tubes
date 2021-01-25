package com.kevin.controller;

import com.kevin.entity.Club;
import com.kevin.entity.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class MatchController {
    @FXML
    private TextField txtScore2;
    @FXML
    private ComboBox<Club> comboClub1;
    @FXML
    private ComboBox<Club> comboClub2;
    @FXML
    private TextField txtScore1;
    private MainController mainController;

    @FXML
    private void addMatchActionBtn(ActionEvent actionEvent) {
        if(txtScore1.getText().trim().isEmpty() || txtScore2.getText().trim().isEmpty() || comboClub1.getValue()==null || comboClub2.getValue()==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tolong isi semua input");
            alert.showAndWait();
        }else if(comboClub1.getValue()==comboClub2.getValue()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tidak bisa memilih tim yang sama");
            alert.showAndWait();
        }else{
            int savedValue1 = Integer.parseInt(txtScore1.getText());
            int savedValue2 = Integer.parseInt(txtScore2.getText());
            Match match=new Match();
            match.setScore1(savedValue1);
            match.setScore2(savedValue2);
            match.setClub1(comboClub1.getValue());
            match.setClub2(comboClub2.getValue());
            try {
                mainController.getMatchDao().addData(match);
                mainController.getMatches().clear();
                mainController.getMatches().addAll(mainController.getMatchDao().showAllData());
                txtScore1.clear();
                txtScore2.clear();
                comboClub1.setValue(null);
                comboClub2.setValue(null);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        comboClub1.setItems(mainController.getClubs());
        comboClub2.setItems(mainController.getClubs());
    }
 }
