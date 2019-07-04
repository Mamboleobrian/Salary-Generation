package controller;

import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database_ueps.ConnectivityClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;

public class rates implements Initializable {

    public AnchorPane anchor_rates;
    public TableView tableview_rate;
    public TableColumn jurisdiction;
    public TableColumn p_rates;
    public TableColumn ovr_rates;
    public JFXComboBox combo_select;
    public JFXTextField new_rate;
    public JFXComboBox select_rate;

    ObservableList<TableModelr> observableList = FXCollections.observableArrayList();
    private ObservableList selections = FXCollections.observableArrayList();


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

        myCombo(this.combo_select);
        Combo(this.select_rate);

    }

    public void myCombo(ComboBox jurisdiction) {
        selections.removeAll(selections);
        String four_jobs [] = {"ICT MANAGER", "ASS ICT MANAGER", "SECRETARY", "ICT SUPPORT"};
        for (String Jobs : four_jobs ){
            selections.addAll(Jobs);



        }
        Collections.sort(selections);
        jurisdiction.getItems().addAll(selections);

    }



    public void Combo(ComboBox rates) {
        selections.removeAll(selections);
        String two_jobs [] = {"NORMAL RATES PER HR", "OVERTIME RATES PER HR"};
        for (String Jobs : two_jobs ){
            selections.addAll(Jobs);
    }
        Collections.sort(selections);
        rates.getItems().addAll(selections);
    }

    public void change(MouseEvent mouseEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to set '"+new_rate.getText()+"' as new rate for '"+combo_select.getValue()+"' as '"+select_rate.getValue()+"'");
        alert.setTitle("Account deletion");
        alert.setHeaderText(null);

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {

        ConnectivityClass connectivityClass = new ConnectivityClass();
        Connection connection = connectivityClass.getConnection();



        }

    }

    public void help(MouseEvent mouseEvent) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("HELP");
        alert1.setHeaderText(null);
        alert1.setContentText("Select jurisdiction then enter New Rate Per Hour And ");
        alert1.showAndWait();
    }
}
