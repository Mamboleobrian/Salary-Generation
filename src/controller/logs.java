package controller;

import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import database_ueps.ConnectivityClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class logs implements Initializable {


    public TableColumn<TableModel, String> tarehe;
    public TableColumn<TableModel, String> kadi;
    public TableColumn<TableModel, String> kufika;
    public TableColumn<TableModel, String> kutoka;
    public TableView<TableModel> tableview;
    public AnchorPane logs_anchor;
    public TableColumn stafu;
    public TableColumn saa;
    ObservableList<TableModel> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        new SlideInRight(logs_anchor).play();
        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select date, card_id, staff_id, time_in, time_out, diff from logs order by date desc");

            while (resultSet.next()){
                observableList.add(new TableModel(resultSet.getString("date"),resultSet.getString("card_id"),resultSet.getString("staff_id"),resultSet.getString("time_in"),resultSet.getString("time_out"),resultSet.getString("diff")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        tarehe.setCellValueFactory(new PropertyValueFactory<>("date"));
        kadi.setCellValueFactory(new PropertyValueFactory<>("card_id"));
        stafu.setCellValueFactory(new PropertyValueFactory<>("staff_id"));
        kufika.setCellValueFactory(new PropertyValueFactory<>("arrival_time"));
        kutoka.setCellValueFactory(new PropertyValueFactory<>("departure_time"));
        saa.setCellValueFactory(new PropertyValueFactory<>("time"));
        tableview.setItems(observableList);

    }

}
