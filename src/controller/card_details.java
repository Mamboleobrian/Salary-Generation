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

public class card_details implements Initializable {

    public TableColumn<TableModeld, String> smart_card;
    public TableColumn<TableModeld, String> staff_name;
    public TableColumn<TableModeld, String> staff_id;
    public TableView<TableModeld> tableviewd;
    public AnchorPane details_anchor;
    public TableColumn jurisdiction;
    ObservableList<TableModeld> observableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        new SlideInRight(details_anchor).play();

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();

        try {
            ResultSet resultSet = connection.createStatement().executeQuery("select Card_ID, Name, ID, jurisdiction from details");

            while (resultSet.next()){
                observableList.add(new TableModeld(resultSet.getString("Card_ID"),resultSet.getString("Name"),resultSet.getString("ID"),resultSet.getString("jurisdiction")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        smart_card.setCellValueFactory(new PropertyValueFactory<>("card"));
        staff_name.setCellValueFactory(new PropertyValueFactory<>("names"));
        staff_id.setCellValueFactory(new PropertyValueFactory<>("id_staff"));
        jurisdiction.setCellValueFactory(new PropertyValueFactory<>("jurisdictions"));
        tableviewd.setItems(observableList);

    }
}
