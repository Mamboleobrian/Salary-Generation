package controller;

import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database_ueps.ConnectivityClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

public class allowances implements Initializable {


    public TableView tableview_allowance;
    public TableColumn jurisdiction_a;
    public TableColumn house;
    public TableColumn transport;
    public JFXComboBox select_jury;
    public JFXComboBox select_allowance;
    public JFXTextField new_amount;
    public AnchorPane anchor_allowances;

    ObservableList<TableModelAllowance> observableList = FXCollections.observableArrayList();
    private ObservableList selections = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {

        new SlideInRight(anchor_allowances).play();

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();

        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery("select jurisdiction, house_allowance, transport_allowance from payment_rate");


            while (resultSet.next()){

                observableList.add(new TableModelAllowance(resultSet.getString("jurisdiction"),resultSet.getString("house_allowance"),resultSet.getString("transport_allowance")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jurisdiction_a.setCellValueFactory(new PropertyValueFactory<>("juries"));
        house.setCellValueFactory(new PropertyValueFactory<>("home"));
        transport.setCellValueFactory(new PropertyValueFactory<>("transport"));
        tableview_allowance.setItems(observableList);

        Combojury(this.select_jury);
        Comboallowance(this.select_allowance);


    }

    public void Combojury(ComboBox jurisdiction) {
        selections.removeAll(selections);
        String four_jobs [] = {"ICT MANAGER", "ASS ICT MANAGER", "SECRETARY", "ICT SUPPORT"};
        for (String Jobs : four_jobs ){
            selections.addAll(Jobs);



        }
        Collections.sort(selections);
        jurisdiction.getItems().addAll(selections);

    }



    public void Comboallowance(ComboBox allow) {
        selections.removeAll(selections);
        String two_jobs [] = {"HOUSE ALLOWANCE", "TRANSPORT ALLOWANCE"};
        for (String Jobs : two_jobs ){
            selections.addAll(Jobs);
        }
        Collections.sort(selections);
        allow.getItems().addAll(selections);
    }


    public void change(MouseEvent mouseEvent) {
    }

    public void help_allowance(MouseEvent mouseEvent) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("HELP");
        alert1.setHeaderText(null);
        alert1.setContentText("Select jurisdiction, select allowance, enter amount then click change");
        alert1.showAndWait();
    }
}
