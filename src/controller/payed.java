package controller;

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

public class payed implements Initializable {
    public AnchorPane anchor_payed;


    public TableView tableview_payed;
    public TableColumn staff_id;
    public TableColumn basic_salary;
    public TableColumn house_all;
    public TableColumn transport_all;
    public TableColumn nhif_;
    public TableColumn nssf_;
    public TableColumn paye;
    public TableColumn net_salo;
    public TableColumn worked_from;
    public TableColumn worked_to;
    public TableColumn time_payed;
    public TableColumn date_payed;

    ObservableList<TableModelpayed> observableList = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        new SlideInRight(anchor_payed).play();

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();


        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery("select staff_id, amount, house_allowance, transport_allowance, nhif, nssf, paye, net, date_from, date_to, time_payed, date_payed from payed order by date_payed desc");



        while (resultSet.next()) {

                observableList.add(new TableModelpayed(resultSet.getString("staff_id"), resultSet.getString("amount"), resultSet.getString("house_allowance"), resultSet.getString("transport_allowance"), resultSet.getString("nhif"), resultSet.getString("nssf")
                        , resultSet.getString("paye"), resultSet.getString("net"), resultSet.getString("date_from"), resultSet.getString("date_to"), resultSet.getString("time_payed"), resultSet.getString("date_payed")));
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }



        staff_id.setCellValueFactory(new PropertyValueFactory<>("_staff"));
        basic_salary.setCellValueFactory(new PropertyValueFactory<>("_basic"));
        house_all.setCellValueFactory(new PropertyValueFactory<>("_house"));
        transport_all.setCellValueFactory(new PropertyValueFactory<>("_transport"));
        nhif_.setCellValueFactory(new PropertyValueFactory<>("_nhif"));
        nssf_.setCellValueFactory(new PropertyValueFactory<>("_nssf"));
        paye.setCellValueFactory(new PropertyValueFactory<>("_paye"));
        net_salo.setCellValueFactory(new PropertyValueFactory<>("_net"));
        worked_from.setCellValueFactory(new PropertyValueFactory<>("_worked_from"));
        worked_to.setCellValueFactory(new PropertyValueFactory<>("_worked_to"));
        time_payed.setCellValueFactory(new PropertyValueFactory<>("_time"));
        date_payed.setCellValueFactory(new PropertyValueFactory<>("_date"));
        tableview_payed.setItems(observableList);
    }

}
