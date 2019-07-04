package controller;

import animatefx.animation.SlideInRight;
import animatefx.animation.SlideInUp;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import database_ueps.ConnectivityClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;

public class new_smartcard implements Initializable {
   
    public AnchorPane anchor_employee;
    public JFXTextField addcard_id;
    public JFXTextField addname;
    public JFXTextField addstaff_id;
    public JFXComboBox select_juri;
    private ObservableList selections = FXCollections.observableArrayList();


    public void add_employee(MouseEvent mouseEvent) {

       // event = actionEvent;
        ConnectivityClass connectionClass = new ConnectivityClass();
        Connection connection = connectionClass.getConnection();
        String sql = "INSERT INTO details(Card_ID, Name, ID, jurisdiction) VALUES('" + addcard_id.getText().toUpperCase() + "','" + addname.getText().toUpperCase() + "','" + addstaff_id.getText().toUpperCase() + "','"+ select_juri.getValue() +"')";

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Alert confirm_details = new Alert(Alert.AlertType.CONFIRMATION);
            confirm_details.setContentText("Add " + addcard_id.getText().toUpperCase() + " Name " + addname.getText().toUpperCase() + " Staff_ID " + addstaff_id.getText().toUpperCase()+ "Jurisdiction" +select_juri.getValue());
            confirm_details.setTitle("Confirm Employee details");
            confirm_details.setHeaderText(null);
            Optional<ButtonType> confirm = confirm_details.showAndWait();
            if (confirm.get() == ButtonType.OK) {
                statement.executeUpdate(sql);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Employee added successfully!");
                alert.setTitle("Message");
                alert.setHeaderText(null);

                alert.showAndWait();
                addcard_id.setText(null);
                addname.setText(null);
                addstaff_id.setText(null);


            }

        } catch(SQLException e)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Employee not added!");
            alert.setTitle("Employee Addition");
            alert.setHeaderText(null);

            alert.showAndWait();

            e.printStackTrace();
        }

    }
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInRight(anchor_employee).play();
        myCombo(this.select_juri);

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


   /* @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SlideInRight(anchor_employee).play();}*/
}
