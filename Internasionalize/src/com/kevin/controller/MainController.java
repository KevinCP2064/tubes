package com.kevin.controller;

import com.kevin.Main;
import com.kevin.dao.ClubDaoImpl;
import com.kevin.dao.MatchDaoImpl;
import com.kevin.entity.Club;
import com.kevin.entity.Match;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private BorderPane borderRoot;
    @FXML
    private TableView<Club> clubView;
    @FXML
    private TableColumn<Club,String> clubCol;
    @FXML
    private TableView<Match> matchView;
    @FXML
    private TableColumn<Match,Club> club1Col;
    @FXML
    private TableColumn<Match,Club> club2Col;
    @FXML
    private TableColumn<Match,Integer> score1Col;
    @FXML
    private TableColumn<Match,Integer> score2Col;
    @FXML
    private TableColumn<Match,String> resultCol;
    private ObservableList<Club> clubs;
    private ObservableList<Match> matches;
    private ClubDaoImpl clubDao;
    private MatchDaoImpl matchDao;
    @FXML
    private Button buttonAddMatches;
    @FXML
    private Label labelFootClub;

    public ObservableList<Club> getClubs() {
        if (clubs == null) {
            clubs = FXCollections.observableArrayList();
            clubs.addAll(getClubDao().showAllData());
        }
        return clubs;
    }

    public ObservableList<Match> getMatches() {
        if (matches == null) {
            matches = FXCollections.observableArrayList();
            matches.addAll(getMatchDao().showAllData());
        }
        return matches;
    }

    public ClubDaoImpl getClubDao() {
        if (clubDao == null) {
            clubDao = new ClubDaoImpl();
        }
        return clubDao;
    }

    public MatchDaoImpl getMatchDao() {
        if (matchDao == null) {
            matchDao = new MatchDaoImpl();
        }
        return matchDao;
    }

    @FXML
    private void addClubActionBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AddClubLayout.fxml"));
        GridPane root = loader.load();
        ClubController controller = loader.getController();
        controller.setMainController(this);
        Scene scene = new Scene(root);
        Stage clubStage=new Stage();
        clubStage.setTitle("Add Club");
        clubStage.setScene(scene);
        clubStage.initOwner(borderRoot.getScene().getWindow());
        clubStage.show();
    }

    @FXML
    private void addMatchesActionBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AddMatchLayout.fxml"));
        GridPane root = loader.load();
        MatchController controller = loader.getController();
        controller.setMainController(this);
        Scene scene = new Scene(root);
        Stage matchStage=new Stage();
        matchStage.setTitle("Add Matches Dialogue");
        matchStage.setScene(scene);
        matchStage.initOwner(borderRoot.getScene().getWindow());
        matchStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clubDao=new ClubDaoImpl();
        matchDao=new MatchDaoImpl();
        clubs= FXCollections.observableArrayList();
        matches= FXCollections.observableArrayList();

        try {
            clubs.addAll(clubDao.fetchAll());
            matches.addAll(matchDao.fetchAll());
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        clubView.setItems(clubs);
        clubCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getName()));


        matchView.setItems(matches);
        club1Col.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getClub1()));
        club2Col.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue().getClub2()));
        score1Col.setCellValueFactory(data-> new SimpleIntegerProperty(data.getValue().getScore1()).asObject());
        score2Col.setCellValueFactory(data-> new SimpleIntegerProperty(data.getValue().getScore2()).asObject());
        resultCol.setCellValueFactory(data -> {
            var first_score = new SimpleIntegerProperty(data.getValue().getScore1());
            var second_score  = new SimpleIntegerProperty(data.getValue().getScore2());

            return Bindings.when(first_score.greaterThan(second_score))
                    .then("WON")
                    .otherwise("LOSE");
        });
    }

    @FXML
    private void setEN(ActionEvent actionEvent) throws IOException {
        Locale l=new Locale("EN");
        LoadView(l);
    }

    @FXML
    private void setIN(ActionEvent actionEvent) throws IOException {
        Locale l=new Locale("IN");
        LoadView(l);
    }

    public void LoadView(Locale locale){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainLayout.fxml"));
        loader.setResources(ResourceBundle.getBundle("bundle",locale));
        try {
            Parent root=loader.load();
            Stage s= (Stage) buttonAddMatches.getScene().getWindow();
            s.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
