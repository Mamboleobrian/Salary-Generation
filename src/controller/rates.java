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

public class rates implements Initializable {

    public AnchorPane anchor_rates;
    public TableView tableview_rate;
    public TableColumn jurisdiction;
    public TableColumn p_rates;
    public TableColumn ovr_rates;
    ObservableList<TableModelr> observableList = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        new SlideInRight(anchor_rates).play();
        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();

        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery("select jurisdiction, rate, ovr from payment_rate");


        while (resultSet.next()){

                observableList.add(new TableModelr(resultSet.getString("jurisdiction"),resultSet.getString("rate"),resultSet.getString("ovr")));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jurisdiction.setCellValueFactory(new PropertyValueFactory<>("job_juri"));
        p_rates.setCellValueFactory(new PropertyValueFactory<>("pay_rates"));
        ovr_rates.setCellValueFactory(new PropertyValueFactory<>("overtime"));
        tableview_rate.setItems(observableList);
    }
}
